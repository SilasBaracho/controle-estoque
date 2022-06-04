package br.com.zitrushealthtech.api.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.zitrushealthtech.api.dto.response.ResultadoCategoria;
import br.com.zitrushealthtech.api.dto.response.ResultadoProduto;
import br.com.zitrushealthtech.api.entity.MovimentacaoEstoque;
import br.com.zitrushealthtech.api.repository.MovimentacaoEstoqueRepository;

@Service
public class MovimentacaoEstoqueService {

  @Autowired
  private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

  @Autowired
  private ProdutoService produtoService;

  /**
   * Metódo resposável persistir os dados na tabela [MOVIMENTACAO_ESTOQUE]
   * 
   * @param MovimentacaoEstoque
   * @return MovimentacaoEstoque
   * @throws Exception
   */
  public MovimentacaoEstoque salvarMovimentacaoEstoque(MovimentacaoEstoque movimentacaoEstoque) throws Exception {

    produtoService.listarProdutoPorId(movimentacaoEstoque.getIdProduto())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Produto não encontrado, verifique o id informado."));

    return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
  }

  /**
   * Metódo resposável por buscar os dados na tabela [MOVIMENTACAO_ESTOQUE]
   * 
   * @param UUID id
   * @return MovimentacaoEstoque
   * @throws Exception
   */
  public Optional<List<MovimentacaoEstoque>> listarMovimentacaoEstoquePorIdProduto(UUID id) {
    return movimentacaoEstoqueRepository.findAllByIdProduto(id);
  }

  /**
   * Metódo resposável por consultar produtos pela categoria
   * 
   * @param Long id
   * @return ResultadoCategoria
   * @throws Exception
   */
  public List<ResultadoCategoria> getQueryCategoria(Long id) {

    List<Object[]> lista = movimentacaoEstoqueRepository.queryCategoria(id);

    List<ResultadoCategoria> listaResultadoCategoria = new ArrayList<ResultadoCategoria>();

    for (Object[] row : lista) {

      ResultadoCategoria resultadoCategoria = new ResultadoCategoria();

      resultadoCategoria.setIdProduto((String) row[0]);
      resultadoCategoria.setNomeProduto((String) row[1]);
      resultadoCategoria.setQuantidadeDisponivel((Integer) row[2]);
      resultadoCategoria.setQuantidadeSaida((BigInteger) row[3]);

      listaResultadoCategoria.add(resultadoCategoria);

    }

    return listaResultadoCategoria;
  }

  /**
   * Metódo resposável por consultar produtos pelo ID
   * 
   * @param UUID id
   * @return ResultadoCategoria
   * @throws Exception
   */
  public ArrayList<ResultadoProduto> getQueryProduto(UUID id) {
    List<Object[]> lista = movimentacaoEstoqueRepository.queryProduto(id);

    ArrayList<ResultadoProduto> listaResultadoProduto = new ArrayList<ResultadoProduto>();

    String idProduto = null;
    String nomeProduto = null;
    BigDecimal totalSaida = new BigDecimal(0);
    BigDecimal totalLucro = new BigDecimal(0);

    BigDecimal quantidadeVendida = new BigDecimal(0);
    BigDecimal valorControle = new BigDecimal(0);
    BigDecimal valorVenda = new BigDecimal(0);
    BigDecimal valorFornecedor = new BigDecimal(0);

    for (Object[] row : lista) {
      quantidadeVendida = new BigDecimal((BigInteger) row[5]);

      idProduto = (String) row[0];
      nomeProduto = (String) row[1];

      if (quantidadeVendida.compareTo(new BigDecimal("0")) > 0) {

        valorFornecedor = (BigDecimal) row[2];
        valorVenda = (BigDecimal) row[3];

        valorControle = valorVenda.subtract(valorFornecedor).multiply(quantidadeVendida);

        totalLucro = totalLucro.add(valorControle);

        totalSaida = totalSaida.add(quantidadeVendida);

      }
    }

    ResultadoProduto resultadoProduto = new ResultadoProduto();
    resultadoProduto.setIdProduto(idProduto);
    resultadoProduto.setNome(nomeProduto);
    resultadoProduto.setTotalLucro(totalLucro);
    resultadoProduto.setTotalSaida(totalSaida);

    listaResultadoProduto.add(resultadoProduto);

    return listaResultadoProduto;
  }

}