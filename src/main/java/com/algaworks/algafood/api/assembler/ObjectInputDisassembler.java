package com.algaworks.algafood.api.assembler;

import java.lang.reflect.ParameterizedType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;

/**
 * Classe abstrata genérica para implementar
 * a desmontagem de um objeto input 
 * 
 * @author Josemar de Mendonça Flausino em 21/07/2020
 *
 * @param <I> Input
 * @param <D> Domain
 */
public abstract class ObjectInputDisassembler<I, D> {

	@Autowired 
	@Getter
	protected ModelMapper mapper;
	
	private final Class<D> domainObject;
		
	@SuppressWarnings("unchecked")
	public ObjectInputDisassembler() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		
		this.domainObject = (Class<D>) type.getActualTypeArguments()[1];
	}
	
	public D toDomainObject(I inputObject) {
		return this.mapper.map(inputObject, this.domainObject);
	}
	
	public void copyToDomainObject(I inputObject, D domainObject) {
		mapper.map(inputObject, domainObject);
	}
}
