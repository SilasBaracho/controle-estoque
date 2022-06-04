package br.com.zitrushealthtech.api.controller;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zitrushealthtech.api.entity.Produto;
import br.com.zitrushealthtech.api.service.ProdutoService;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ModelMapper modelMapper;

  /**
   * Endpoint resposável persistir os dados na tabela [PRODUTO]
   * 
   * @param MovimentacaoEstoque
   * @return MovimentacaoEstoque
   * @throws Exception
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void salvarProduto(@RequestBody Produto produto) throws Exception {
    produtoService.salvarProduto(produto);
  }

  /**
   * Endpoint resposável buscar os dados na tabela [PRODUTO]
   * 
   * @param UUID id
   * @return Produto
   */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Produto listarProdutoPorId(@PathVariable("id") UUID id) {
    return produtoService.listarProdutoPorId(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Produto não encontrado, verifique o id informado."));
  }

  /**
   * Endpoint responsável por buscar os dados na tabela [PRODUTO]
   * 
   * @return List<Produto>
   */
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<Produto> listarProdutos() {
    return produtoService.listarProdutos();
  }

  /**
   * Endpoint responsável por deletar o produto
   * 
   * @param UUID id
   * @return HttpStatus.NO_CONTENT
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletarProdutoPorId(@PathVariable("id") UUID id) {
    produtoService.listarProdutoPorId(id)
        .map(produto -> {
          produtoService.deletarProdutoPorId(produto.getIdProduto());
          return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Produto não encontrado, verifique o id informado."));
  }

  /**
   * Endpoint responsável atualizar o produto
   * 
   * @param UUID id
   * @return HttpStatus.NO_CONTENT
   */
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void alterarProdutoPorId(@PathVariable("id") UUID id, @RequestBody Produto request) {
    produtoService.listarProdutoPorId(id)
        .map(produtoBase -> {
          try {
            modelMapper.map(request, produtoBase);

            produtoService.atualizarProduto(produtoBase);
          } catch (Exception e) {
            e.printStackTrace();
          }
          return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Produto não encontrado, verifique o id informado."));
  }

}