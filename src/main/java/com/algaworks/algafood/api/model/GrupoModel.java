package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Grupo", description = "Representa um grupo de usuários")
@Setter
@Getter
public class GrupoModel {

	@ApiModelProperty(example = "1")
    private Long id;
	
	@ApiModelProperty(example = "Gerente")
    private String nome;
    
}
