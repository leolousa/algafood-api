package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Pedido;

/**
 * Classe de repositório com o uso de Specification
 * para pesquisa com muitas propriedades
 * 
 * @author Leonardo
 *
 */
@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long>,
	JpaSpecificationExecutor<Pedido> {

	// Utilizamos JPQL aqui para evitar que o JPA faça vários selects para trazer os dados
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
	
	// Busca por código (UUID)
	Optional<Pedido> findByCodigo(String codigo);
	
	boolean isPedidoGerenciadoPor(String codigoPedido, Long usuarioId);
}
