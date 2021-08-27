package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para Restaurante
 * 
 * @author Leonardo
 *
 */

@Setter
@Getter
public class RestauranteResumoModel {

	@ApiModelProperty(example = "1")
    private Long id;
	
	@ApiModelProperty(example = "Thai Gourmet")
    private String nome;   
} 
