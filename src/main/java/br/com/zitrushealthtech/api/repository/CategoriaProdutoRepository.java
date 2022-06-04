package br.com.zitrushealthtech.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zitrushealthtech.api.entity.CategoriaProduto;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {

  public Optional<CategoriaProduto> findByDescricaoCategoria(String descricaoCategoria);

}