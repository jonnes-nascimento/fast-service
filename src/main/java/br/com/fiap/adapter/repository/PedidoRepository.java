package br.com.fiap.adapter.repository;

import br.com.fiap.adapter.repository.jpa.ClienteJpaRepository;
import br.com.fiap.adapter.repository.jpa.PedidoJpaRepository;
import br.com.fiap.adapter.repository.jpa.ProdutoJpaRepository;
import br.com.fiap.adapter.repository.mapper.PedidoMapper;
import br.com.fiap.adapter.repository.model.PedidoJpa;
import br.com.fiap.core.domain.exception.ClienteNotFoundException;
import br.com.fiap.core.domain.exception.PedidoNotFoundException;
import br.com.fiap.core.domain.exception.PersistenceException;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.request.PedidoCreateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.port.out.PedidoLoadOutputPort;
import br.com.fiap.core.port.out.PedidoSaveOutputPort;

import java.util.List;
import java.util.Optional;

public class PedidoRepository implements
        PedidoSaveOutputPort,
        PedidoLoadOutputPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final ProdutoJpaRepository produtoJpaRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoRepository(PedidoJpaRepository pedidoJpaRepository,
                            ClienteJpaRepository clienteJpaRepository,
                            ProdutoJpaRepository produtoJpaRepository,
                            PedidoMapper pedidoMapper) {
        this.pedidoJpaRepository = pedidoJpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
        this.produtoJpaRepository = produtoJpaRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Override
    public PedidoResponse save(PedidoCreateRequest pedidoCreateRequest) {
        try {

            var clienteJpa = clienteJpaRepository.findById(pedidoCreateRequest.cliente_id())
                    .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado (ID: " + pedidoCreateRequest.cliente_id() + ")"));
            var produtoJpaList = pedidoCreateRequest.produtos_id()
                    .stream()
                    .map(produtoJpaRepository::findById)
                    .flatMap(Optional::stream)
                    .toList();
            var pedidoJpa = new PedidoJpa(clienteJpa,
                    pedidoCreateRequest.numero(),
                    pedidoCreateRequest.status_pedido(),
                    produtoJpaList);

            var pedidoSaved = pedidoJpaRepository.save(pedidoJpa);

            return pedidoMapper.toPedidoResponse(pedidoSaved);
        } catch (RuntimeException ex) {
            throw new PersistenceException("Ocorreu um erro ao tentar processar o cadastro do pedido", ex);
        }
    }

    @Override
    public PedidoResponse loadByNumero(String numero) {
        var pedido = pedidoJpaRepository.findByNumero(numero)
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado (NUMERO: " + numero + ")"));
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
    public List<PedidoResponse> loadByStatus(PedidoStatus status) {
        return pedidoJpaRepository.findByStatusPedido(status)
                .stream()
                .map(pedidoMapper::toPedidoResponse)
                .toList();
    }
}
