package br.com.zitrushealthtech.api.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class MovimentacaoEstoque {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idMovimentacaoEstoque;

  @GeneratedValue(generator = "UUID")
  private UUID idProduto;

  private String tpMovimentacao;

  private Long quantidade;

  // private BigDecimal valorVenda;

  private Date createdAt;

}