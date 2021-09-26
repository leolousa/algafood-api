package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, 
	RestauranteRepositoryCustomQueries, JpaSpecificationExecutor<Restaurante> {
	
	// Método para evitar o problema de N+1 (muitos selects)
	// Usamos left join fetch para caso um restaurante não possuir 
	// formas de pagamento ele não seja filtrado em um inner join.
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
	
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);

	//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")// Mesmo que o método acima
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha); // Usando o arquivo orm.xml

	List<Restaurante> findTop2ByNomeContaining(String nome);

	int countByCozinhaId(Long cozinha);
	
	boolean existsResponsavel(Long restauranteId, Long usuarioId);
	
}
