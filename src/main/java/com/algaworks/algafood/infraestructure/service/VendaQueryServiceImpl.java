package com.algaworks.algafood.infraestructure.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.StatusPedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
/**
 * Classe de implementação de VendaQueryService
 * podendo ter cálculos ou transformação de dados
 * 
 * @author Leonardo
 *
 */
@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);// tipo que a consulta retorna
		var root = query.from(Pedido.class);
		
		var functioConvertTzDataCriacao = builder.function(
				"convert_tz", Date.class, root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
		
		var funtionDateDataCriacao = builder.function(
				"date", Date.class, functioConvertTzDataCriacao); //Evocar a função Date do MySQL convertendo o TimeZone
		
		var selection = builder.construct(VendaDiaria.class,
				funtionDateDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		var predicates = new ArrayList<Predicate>();
		
		if (filtro.getRestauranteId() != null) {
		    predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		}
		    
		if (filtro.getDataCriacaoInicio() != null) {
		    predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), 
		            filtro.getDataCriacaoInicio()));
		}

		if (filtro.getDataCriacaoFim() != null) {
		    predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), 
		            filtro.getDataCriacaoFim()));
		}
		    
		predicates.add(root.get("status").in(
		        StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		query.select(selection);
		
		query.where(predicates.toArray(new Predicate[0]));
		
		query.groupBy(funtionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}
