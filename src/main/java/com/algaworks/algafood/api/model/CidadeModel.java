package com.algaworks.algafood.api.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe DTO de representação do recurso para Cidade
 * 
 * @author Leonardo
 *
 */
@Getter
@Setter
public class CidadeModel {

	private Long id;
	private String nome;
	private EstadoModel estado;
}
