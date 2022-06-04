package br.com.zitrushealthtech.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zitrushealthtech.api.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

  public Optional<Produto> findByNome(String nome);

}