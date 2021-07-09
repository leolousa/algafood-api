package com.algaworks.algafood.api.model.mixin;

import java.time.LocalDateTime;
import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Classe de configuração das anotações do Jackson
 * e outras anotações relacionadas à API e não à entidade Restaurante.
 * Deixa a classe original(Restaurante) sem customização de recursos da API
 * 
 * @author Leonardo
 *
 */
public abstract class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<Produto> produtos;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento;

}
