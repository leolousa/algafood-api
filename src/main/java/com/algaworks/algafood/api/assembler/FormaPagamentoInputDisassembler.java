package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

/**
 * Classe de conversão de FormaPagamentoModel(DTO) para FormaPagamento(Entidade)
 * extendendo a classe abstrata ObjectInputDisassembler para otimização do uso
 * 
 * @author Leonardo
 *
 */
@Component
public class FormaPagamentoInputDisassembler extends ObjectInputDisassembler<FormaPagamentoInput, FormaPagamento>{

}
