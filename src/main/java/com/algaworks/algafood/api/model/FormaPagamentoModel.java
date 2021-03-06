package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para FormaPagamento
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class FormaPagamentoModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Cartão de crédito")
	private String descricao;
}
