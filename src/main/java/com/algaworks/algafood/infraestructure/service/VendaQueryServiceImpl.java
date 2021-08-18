package com.algaworks.algafood.infraestructure.service;

import java.util.List;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
/**
 * Classe de implementação de VendaQueryService
 * podendo ter cálculos ou transformação de dados
 * 
 * @author Leonardo
 *
 */
public class VendaQueryServiceImpl implements VendaQueryService {

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		return null;
	}

}
