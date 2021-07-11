package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe de representação de entrada de dados para a API
 * @author Leonardo
 *
 */
@Getter
@Setter
public class FormaPagamentoInput {

	@NotBlank
	private String descricao;
}
