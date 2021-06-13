package com.algaworks.algafood.infraestructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.algaworks.algafood.domain.model.Restaurante;

/**
 * Classe factory de Specifications
 * para implementar as buscas específicas
 * utilizando Specification
 * @author Leonardo
 *
 */
public class RestauranteSpecs {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> 
			builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) ->
			builder.like(root.get("nome"), "%" + nome + "%");
			
			// Para remover acentos e caracteres especiais
			//builder.like(root.get("nome"), "%" + 
			//		Normalizer.normalize(nome, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "") 
			//		+ "%");
	}	
}
