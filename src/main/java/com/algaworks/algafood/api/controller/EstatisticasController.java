package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.core.annotation.Offset;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
/**
 * Classe controladora com os endpoints de estatísticas 
 * @author Leonardo
 *
 */
@RestController
@RequestMapping(path = "/estatisticas")
@Validated
public class EstatisticasController {
	
	@Autowired
	private VendaQueryService vendaQueryService;
	
	@GetMapping("/vendas-diarias")
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro,
			@RequestParam(required = false, defaultValue = "+00:00") @Offset String timeOffset) {
		return vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
	}

}
