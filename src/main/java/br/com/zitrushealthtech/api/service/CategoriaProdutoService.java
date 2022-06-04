package br.com.zitrushealthtech.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zitrushealthtech.api.entity.CategoriaProduto;
import br.com.zitrushealthtech.api.repository.CategoriaProdutoRepository;

@Service
public class CategoriaProdutoService {

  @Autowired
  private CategoriaProdutoRepository categoriaProdutoRepository;

  /**
   * Metódo resposável persistir os dados na tabela [CATEGORIA_PRODUTO]
   * 
   * @param CategoriaProduto
   * @return CategoriaProduto
   * @throws Exception
   */
  public CategoriaProduto salvarCategoriaProduto(CategoriaProduto categoriaProduto) throws Exception {

    // Verifica se já existe uma categoria com o mesma descrição
    buscarCategoriaProdutoPelaDescricao(categoriaProduto.getDescricaoCategoria());

    return categoriaProdutoRepository.save(categoriaProduto);
  }

  /**
   * Metódo resposável buscar a categoria pelo ID informado
   * 
   * @param Long idCategoria
   * @return Optional<CategoriaProduto>
   * @throws Exception
   */
  public Optional<CategoriaProduto> listarCategoriaProdutoPorId(Long idCategoria) {
    return categoriaProdutoRepository.findById(idCategoria);
  }

  /**
   * Metódo resposável buscar todas as categorias
   * 
   * @return List<CategoriaProduto>
   * @throws Exception
   */
  public List<CategoriaProduto> listarCategorias() {
    return categoriaProdutoRepository.findAll();
  }

  /**
   * Metódo resposável por verificar se já existe uma categoria com a mesma
   * descrição
   * 
   * @param descricaoCategoria
   * @throws Exception
   */
  public void buscarCategoriaProdutoPelaDescricao(String descricaoCategoria) throws Exception {

    Optional<CategoriaProduto> produtoCategoriaReturn = categoriaProdutoRepository
        .findByDescricaoCategoria(descricaoCategoria);

    boolean ProdutoCategoriaExist = produtoCategoriaReturn.isEmpty() ? false : true;

    if (ProdutoCategoriaExist)
      throw new Exception("Já existe uma categoria cadastrada com a descrição informada");
  }

}