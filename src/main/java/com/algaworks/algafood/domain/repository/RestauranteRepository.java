package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, 
	RestauranteRepositoryCustomQueries, JpaSpecificationExecutor<Restaurante> {
	

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")// Mesmo que o método acima
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha); // Usando o arquivo orm.xml

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);
	

	
}
