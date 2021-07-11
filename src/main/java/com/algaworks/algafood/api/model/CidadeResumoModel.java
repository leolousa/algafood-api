package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso Cidade como resumo
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class CidadeResumoModel {

	private Long id;
	private String nome;
	private String estado;
}
