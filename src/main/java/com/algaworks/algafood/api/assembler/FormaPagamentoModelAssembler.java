package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;

/**
 * Classe de conversão de FormaPagamento(Entidade) para FormaPagamentoModel(DTO)
 * extendendo a classe abstrata ObjectModelAssembler para otimização do uso
 * 
 * @author Leonardo
 *
 */
@Component
public class FormaPagamentoModelAssembler extends ObjectModelAssembler<FormaPagamentoModel, FormaPagamento> {

}
