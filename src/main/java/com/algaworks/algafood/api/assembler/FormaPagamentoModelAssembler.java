package com.algaworks.algafood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;

/**
 * Classe de conversão de FormaPagamento(Entidade) para FormaPagamentoModel(DTO)
 * 
 * @author Leonardo
 *
 */
@Component
public class FormaPagamentoModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	// Método que convOserte FormaPagamento para FormaPagamentoModel
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		return modelMapper.map(formaPagamento, FormaPagamentoModel.class);
	}

	// Método que converte uma lista de FormaPagamento para FormaPagamentoModel
	public List<FormaPagamentoModel> toCollectionModel(List<FormaPagamento> formasPagamentos) {
		return formasPagamentos.stream().map(formaPagamento -> toModel(formaPagamento)).collect(Collectors.toList());
	}
}
