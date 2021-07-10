package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.domain.model.Restaurante;

/**
 * Classe de conversão de Restaurante para RestauranteModel
 * 
 * @author Leonardo
 *
 */
@Component
public class RestauranteModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	// Método que convOserte Restaurante para RestauranteModel
	public RestauranteModel toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteModel.class);
	}

	// Método que converte uma lista de Restaurante para RestauranteModel
	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}
}
