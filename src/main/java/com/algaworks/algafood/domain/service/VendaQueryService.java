package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
/**
 * Interface de serviços de consultas com dados agregados
 * 
 * @author Leonardo
 *
 */
public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
