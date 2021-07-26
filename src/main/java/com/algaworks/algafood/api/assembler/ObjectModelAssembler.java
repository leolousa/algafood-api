package com.algaworks.algafood.api.assembler;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Classe abstrata genérica para implementar
 * a montagem de um objeto Model
 * 
 * @author Leonardo
 *
 * @param <M> Model
 * @param <D> Domain
 */
public abstract class ObjectModelAssembler<M, D> {
	
	@Autowired 
	protected ModelMapper mapper;
	
	private final Class<M> modelObject;
	
		
	@SuppressWarnings("unchecked")
	public ObjectModelAssembler() {
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		
		this.modelObject = (Class<M>) type.getActualTypeArguments()[0];
	}
	
	public M toModel(D domainObject) {
		return  this.mapper.map(domainObject, this.modelObject);
	}
	
	public List<M> toCollectionModel(Collection<D> listOfDomainObjects) {
		return listOfDomainObjects.stream().map(o -> this.toModel(o)).collect(Collectors.toList());
	}
	
}
