package br.com.fiap.adapter.repository.jpa;

import br.com.fiap.adapter.repository.model.PedidoJpa;
import br.com.fiap.core.domain.model.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoJpa, Integer> {
    Optional<PedidoJpa> findByNumero(String numero);

    List<PedidoJpa> findByStatusPedido(PedidoStatus status);
}
