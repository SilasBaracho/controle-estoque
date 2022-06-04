package br.com.zitrushealthtech.api.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoricoAlteracaoProduto {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID idProduto;

  private String nome;

  private String descricao;

  private BigDecimal valorRevenda;

  private BigDecimal valorFornecedor;

  private Long quantidade;

  private Date createdAt;

  private Long idCategoria;
}