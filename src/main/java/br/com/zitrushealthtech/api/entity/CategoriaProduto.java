package br.com.zitrushealthtech.api.entity;

import java.util.Date;

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
public class CategoriaProduto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idCategoria;

  private String descricaoCategoria;

  private Date createdAt;

  private Date updatedAt;

}