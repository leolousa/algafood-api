package com.algaworks.algafood.infraestructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryCustomQueries;

/**
 * Classe que implementa um repository customizado para queries mais complexas
 * 
 * @author Leonardo
 *
 */
@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

		//Implementando buscas com CriteriaQuery
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		criteria.from(Restaurante.class);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();

	}
}
