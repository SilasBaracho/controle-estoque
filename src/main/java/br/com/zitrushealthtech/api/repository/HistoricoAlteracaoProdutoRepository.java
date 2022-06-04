package br.com.zitrushealthtech.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zitrushealthtech.api.entity.HistoricoAlteracaoProduto;

public interface HistoricoAlteracaoProdutoRepository extends JpaRepository<HistoricoAlteracaoProduto, UUID> {

}