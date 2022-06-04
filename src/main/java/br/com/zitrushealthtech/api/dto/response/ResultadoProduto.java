package br.com.zitrushealthtech.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoProduto {

  private String idProduto;

  private String nome;

  private BigDecimal totalSaida;

  private BigDecimal totalLucro;

}
