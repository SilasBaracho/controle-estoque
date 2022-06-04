package br.com.zitrushealthtech.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.zitrushealthtech.api.entity.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {

  public Optional<MovimentacaoEstoque> findByIdProduto(UUID nome);

  public Optional<List<MovimentacaoEstoque>> findAllByIdProduto(UUID id);

  @Query(value = " select Cast(pro.id_produto as varchar) as id_produto, pro.nome as nome_produto, pro.quantidade as quantidade_disponivel, sum(mov.quantidade) as quantidade_saida from Movimentacao_estoque as mov, Produto as pro where mov.id_produto = pro.id_produto and pro.id_categoria = :id and mov.tp_movimentacao = 'S' group by pro.id_produto, pro.nome, pro.quantidade, mov.tp_movimentacao", nativeQuery = true)
  public List<Object[]> queryCategoria(@Param("id") Long id);

  @Query(value = " SELECT Cast(hap.id_produto as varchar), hap.nome, hap.valor_fornecedor, hap.valor_revenda, hap.created_at, (SELECT SUM(me.quantidade) FROM movimentacao_estoque me WHERE me.tp_movimentacao = 'S' AND Cast(me.id_produto as varchar) = Cast(hap.id_produto as varchar) AND me.created_at BETWEEN hap.created_at AND COALESCE(hap.valid_at, CURRENT_TIMESTAMP)) total_vendas FROM (SELECT Cast(hap2.id_produto as varchar), hap2.nome, hap2.valor_fornecedor, hap2.valor_revenda, hap2.created_at, (SELECT hap3.created_at - INTERVAL '1 MICROSECONDS' FROM historico_alteracao_produto hap3 WHERE hap3.id_produto = hap2.id_produto AND hap3.created_at > hap2.created_at ORDER BY hap3.created_at ASC LIMIT 1) valid_at FROM historico_alteracao_produto hap2 WHERE hap2.id_produto = :id AND hap2.quantidade > 0 ) hap ", nativeQuery = true)
  public List<Object[]> queryProduto(@Param("id") UUID id);

}
