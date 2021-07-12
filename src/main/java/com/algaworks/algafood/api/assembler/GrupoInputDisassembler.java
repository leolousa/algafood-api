package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

/**
 * Classe de conversão de GrupoModel(DTO) para Grupo(Entidade)
 * extendendo a classe abstrata ObjectInputDisassembler para otimização do uso
 * 
 * @author Leonardo
 *
 */
@Component
public class GrupoInputDisassembler extends ObjectInputDisassembler<GrupoInput, Grupo>{

}
