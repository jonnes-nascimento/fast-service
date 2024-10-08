package br.com.fiap.adapter.repository.jpa;

import br.com.fiap.adapter.repository.model.ClienteJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteJpa, Integer> {
    Optional<ClienteJpa> findByCpf(String cpf);
}
