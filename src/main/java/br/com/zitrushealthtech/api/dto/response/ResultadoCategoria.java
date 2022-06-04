package br.com.zitrushealthtech.api.dto.response;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultadoCategoria {

  private String idProduto;

  private String nomeProduto;

  private Integer quantidadeDisponivel;

  private BigInteger quantidadeSaida;

}
