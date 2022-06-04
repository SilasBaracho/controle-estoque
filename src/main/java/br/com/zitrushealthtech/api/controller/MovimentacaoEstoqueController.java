package br.com.zitrushealthtech.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zitrushealthtech.api.dto.response.ResultadoCategoria;
import br.com.zitrushealthtech.api.dto.response.ResultadoProduto;
import br.com.zitrushealthtech.api.entity.MovimentacaoEstoque;
import br.com.zitrushealthtech.api.service.MovimentacaoEstoqueService;

@RestController
@RequestMapping("/api/movimentacao-estoque")
public class MovimentacaoEstoqueController {

  @Autowired
  private MovimentacaoEstoqueService movimentacaoEstoqueService;

  /**
   * Endpoint resposável persistir os dados na tabela [MOVIMENTACAO_ESTOQUE]
   * 
   * @param MovimentacaoEstoque
   * @return MovimentacaoEstoque
   * @throws Exception
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void salvarCategoriaProduto(@RequestBody MovimentacaoEstoque movimentacaoEstoque) throws Exception {
    movimentacaoEstoqueService.salvarMovimentacaoEstoque(movimentacaoEstoque);
  }

  /**
   * Endpoint resposável buscar a categoria pelo ID informado
   * 
   * @param UUID idProduto
   * @return Optional<MovimentacaoEstoque>
   * @throws Exception
   */
  @GetMapping("/{idProduto}")
  @ResponseStatus(HttpStatus.OK)
  public List<MovimentacaoEstoque> listarMovimentacaoEstoquePorIdProduto(@PathVariable("idProduto") UUID idProduto) {
    return movimentacaoEstoqueService.listarMovimentacaoEstoquePorIdProduto(idProduto)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Nenhuma movimentação no estoque foi encontrada com o ID informado."));
  }

  /**
   * Endpoint resposável buscar realizar a query pra trazer informações do PRODUTO
   * 
   * @param Long idCategoria
   * @return Optional<MovimentacaoEstoque>
   * @throws Exception
   */
  @GetMapping("/categoria/{idCategoria}")
  @ResponseStatus(HttpStatus.OK)
  public List<ResultadoCategoria> queryCategoria(@PathVariable("idCategoria") Long idCategoria) {
    return movimentacaoEstoqueService.getQueryCategoria(idCategoria);
  }

  /**
   * Endpoint resposável buscar realizar a query pra trazer informações do PRODUTO
   * 
   * @param UUID idProduto
   * @return
   * @return Optional<MovimentacaoEstoque>
   * @throws Exception
   */
  @GetMapping("/produto/{idProduto}")
  @ResponseStatus(HttpStatus.OK)
  public ArrayList<ResultadoProduto> queryProduto(@PathVariable("idProduto") UUID idProduto) {
    return movimentacaoEstoqueService.getQueryProduto(idProduto);
  }

}