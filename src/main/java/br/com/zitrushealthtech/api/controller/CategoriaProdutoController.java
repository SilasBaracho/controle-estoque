package br.com.zitrushealthtech.api.controller;

import java.util.List;

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

import br.com.zitrushealthtech.api.entity.CategoriaProduto;
import br.com.zitrushealthtech.api.service.CategoriaProdutoService;

@RestController
@RequestMapping("/api/produto-categoria")
public class CategoriaProdutoController {

  @Autowired
  private CategoriaProdutoService categoriaProdutoService;

  /**
   * Endpoint resposável persistir os dados na tabela [CATEGORIA_PRODUTO]
   * 
   * @param CategoriaProduto
   * @return CategoriaProduto
   * @throws Exception
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void salvarCategoriaProduto(@RequestBody CategoriaProduto categoriaProduto) throws Exception {
    categoriaProdutoService.salvarCategoriaProduto(categoriaProduto);
  }

  /**
   * Endpoint resposável buscar a categoria pelo ID informado
   * 
   * @param Long idCategoria
   * @return Optional<CategoriaProduto>
   * @throws Exception
   */
  @GetMapping("/{idCategoria}")
  @ResponseStatus(HttpStatus.OK)
  public CategoriaProduto listarCategoriaProdutoPorId(@PathVariable("idCategoria") Long idCategoria) {
    return categoriaProdutoService.listarCategoriaProdutoPorId(idCategoria)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Categoria não encontrada, verifique o código informado."));
  }

  /**
   * Endpoint resposável buscar todas as categorias
   * 
   * @return List<CategoriaProduto>
   * @throws Exception
   */
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<CategoriaProduto> listarCategorias() {
    return categoriaProdutoService.listarCategorias();
  }

}