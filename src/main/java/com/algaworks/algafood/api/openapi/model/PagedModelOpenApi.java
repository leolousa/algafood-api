package com.algaworks.algafood.api.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe genérica para representar o modelo de Pages
 * para a documentação da API. Extender esta classe
 * nas demais classes DTO das Entidades a serem representadas
 * que utilizam o objeto Page do Spring
 * 
 * @author Leonardo
 *
 */
@ApiModel("PagedModel")
@Getter
@Setter
public class PagedModelOpenApi<T> {

List<T> content;
	
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private Long size;
	
	@ApiModelProperty(example = "55", value = "Total de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private Long number;
}
