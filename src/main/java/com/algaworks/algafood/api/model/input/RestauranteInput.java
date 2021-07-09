package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;
/**
 * Classe de representação de entrada de dados para a API
 * @author Leonardo
 *
 */
@Getter
@Setter
public class RestauranteInput {

	@NotBlank
	private String nome;
	
	@NotNull 
	@PositiveOrZero
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha; 
}
