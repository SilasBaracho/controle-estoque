package br.com.zitrushealthtech.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrushealthtech.api.entity.Produto;
import br.com.zitrushealthtech.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  /**
   * Metódo resposável persistir os dados na tabela [Produto]
   * 
   * @param Produto
   * @return Produto
   * @throws Exception
   */
  public Produto salvarProduto(Produto produto) throws Exception {
    buscarProduto(produto.getNome());

    return produtoRepository.save(produto);
  }

  /**
   * Metódo resposável buscar o produto pelo ID informado
   * 
   * @param Long idCategoria
   * @return Optional<Produto>
   * @throws Exception
   */
  public Optional<Produto> listarProdutoPorId(UUID id) {
    return produtoRepository.findById(id);
  }

  /**
   * Metódo resposável buscar os produto
   * 
   * @param Long idCategoria
   * @return Optional<Produto>
   * @throws Exception
   */
  public List<Produto> listarProdutos() {
    return produtoRepository.findAll();
  }

  /**
   * Metódo resposável deletar o produto
   * 
   * @param UUID id
   * @return void
   * @throws Exception
   */
  public void deletarProdutoPorId(UUID id) {
    produtoRepository.deleteById(id);
  }

  /**
   * Metódo resposável atualizar o produto
   * 
   * @param Produto
   * @return Produto
   * @throws Exception
   */
  public Produto atualizarProduto(Produto produto) throws Exception {
    return produtoRepository.save(produto);
  }

  /**
   * Metódo resposável por verificar se já existe um produto com o mesmo nome
   * 
   * @param nome
   * @throws Exception
   */
  public void buscarProduto(String nome) throws Exception {

    Optional<Produto> produtoReturn = produtoRepository.findByNome(nome);

    boolean descricaoProdutoExist = produtoReturn.isEmpty() ? false : true;

    if (descricaoProdutoExist)
      throw new Exception("Já existe um produto cadastrado com o nome informado.");

  }

}