package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe de configuração das anotações do Jackson
 * e outras anotações relacionadas à API e não à entidade Cidade.
 * Deixa a classe original(Cidade) sem customização de recursos da API
 * 
 * @author Leonardo
 *
 */
public class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
	
}
