package com.algaworks.algafood.infraestructure.service;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
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
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiaria.class);// tipo que a consulta retorna
		var root = query.from(Pedido.class);
		
		var funtionDateDataCriacao = builder.function(
				"date", Date.class, root.get("dataCriacao")); //Evocar a função Date do MySQL
		
		var selection = builder.construct(VendaDiaria.class,
				funtionDateDataCriacao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		
		query.groupBy(funtionDateDataCriacao);
		
		return manager.createQuery(query).getResultList();
	}

}
