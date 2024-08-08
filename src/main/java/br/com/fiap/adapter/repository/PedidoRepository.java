package br.com.fiap.adapter.repository;

import br.com.fiap.adapter.repository.jpa.PedidoJpaRepository;
import br.com.fiap.adapter.repository.mapper.PedidoMapper;
import br.com.fiap.core.domain.exception.PersistenceException;
import br.com.fiap.core.domain.exception.PedidoNotFoundException;
import br.com.fiap.core.domain.model.Pedido;
import br.com.fiap.core.domain.model.request.PedidoUpdateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.port.out.PedidoLoadOutputPort;
import br.com.fiap.core.port.out.PedidoSaveOutputPort;


import java.util.List;

public class PedidoRepository implements
        PedidoSaveOutputPort,
        PedidoLoadOutputPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoRepository(PedidoJpaRepository pedidoJpaRepository,
                            PedidoMapper pedidoMapper) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoResponse save(Pedido pedido) {
        try {
            var pedidoJpa = pedidoMapper.toPedidoJpa(pedido);
            var pedidoSaved = pedidoJpaRepository.save(pedidoJpa);
            return pedidoMapper.toPedidoResponse(pedidoSaved);
        } catch (RuntimeException ex) {
            throw new PersistenceException("ocorreu um erro ao tentar processar o cadastro do pedido", ex);
        }
    }

    @Override
    public PedidoResponse update(PedidoUpdateRequest pedidoUpdateRequest) {
        try {
            var pedidoJpa = pedidoJpaRepository.findById(pedidoUpdateRequest.id())
                    .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado (ID: " + pedidoUpdateRequest.id() + ")"));

            pedidoJpa..(pedidoUpdateRequest.categoria());
            pedidoJpa.setDescricao(pedidoUpdateRequest.descricao());
            pedidoJpa.setNome(pedidoUpdateRequest.nome());
            pedidoJpa.setPreco(pedidoUpdateRequest.preco());

            pedidoJpa = pedidoJpaRepository.save(pedidoJpa);

            return pedidoMapper.toPedidoResponse(pedidoJpa);
        } catch (RuntimeException ex) {
            throw new PersistenceException("Ocorreu um erro ao tentar processar a alteração do pedido", ex);
        }
    }

    @Override
    public PedidoResponse loadById(int id) {
        var pedido = pedidoJpaRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado (ID: " + id + ")"));
        return pedidoMapper.toPedidoResponse(pedido);
    }


    @Override
    public List<PedidoResponse> loadAll() {
        return pedidoJpaRepository.findAll()
                .stream()
                .map(pedidoMapper::toPedidoResponse)
                .toList();
    }

    @Override
    public void delete(int id) {
        var pedidoJpa = pedidoJpaRepository.findById(id)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado (ID: " + id + ")"));
        pedidoJpaRepository.delete(pedidoJpa);
    }
}
