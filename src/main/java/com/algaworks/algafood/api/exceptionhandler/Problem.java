package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "2021-08-25T14:19:30Z", value = "Data e hora no formato ISO", position = 5)
	private OffsetDateTime timeStamp;

	@ApiModelProperty(example = "https://algafood.com.br/erro-negocio", position = 10)
	private String type;
	
	@ApiModelProperty(example = "Violação de regra de negócio", position = 15)
	private String title;
	
	@ApiModelProperty(example = "Não existe um cadastro de restaurante com código 10", position = 20)
	private String detail;
	
	@ApiModelProperty(example = "Não existe um cadastro de restaurante com código 10", position = 25)
	private String userMessage;
	
	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opicional)",
			position = 30)
	private List<Object> objects;
	
	
	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		
		@ApiModelProperty(example = "Preço")
		private String name;
		
		@ApiModelProperty(example = "O preço é obrigatório")
		private String userMessage;
		
	}
}
