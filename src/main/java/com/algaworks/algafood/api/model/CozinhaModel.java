package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para Cozinha
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class CozinhaModel {
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Brasileira")
	private String nome;
	
}
