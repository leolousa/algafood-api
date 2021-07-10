package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para Restaurante
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class RestauranteModel {

	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaModel cozinha;
	
}
