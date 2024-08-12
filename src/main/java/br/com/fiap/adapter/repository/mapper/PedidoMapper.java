package br.com.fiap.adapter.repository.mapper;

import br.com.fiap.adapter.repository.model.PedidoJpa;
import br.com.fiap.core.domain.model.Pedido;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    private final ClienteMapper clienteMapper;
    private final ProdutoMapper produtoMapper;

    public PedidoMapper(ClienteMapper clienteMapper,
                        ProdutoMapper produtoMapper) {
        this.clienteMapper = clienteMapper;
        this.produtoMapper = produtoMapper;
    }

    public PedidoJpa toPedidoJpa(Pedido pedido) {
        return new PedidoJpa(
                clienteMapper.toClienteJpa(pedido.getCliente()),
                pedido.getNumero(),
                pedido.getStatusPedido(),
                produtoMapper.toProdutoJpaList(pedido.getProdutos())
        );
    }

    public Pedido toPedido(PedidoJpa pedidoJpa) {
        return new Pedido(
                clienteMapper.toCliente(pedidoJpa.getCliente()),
                pedidoJpa.getNumero(),
                pedidoJpa.getStatusPedido(),
                produtoMapper.toProdutoList(pedidoJpa.getProdutos())
        );
    }

    public PedidoResponse toPedidoResponse(PedidoJpa pedidoJpa) {
        return new PedidoResponse(
                pedidoJpa.getId(),
                clienteMapper.toCliente(pedidoJpa.getCliente()),
                pedidoJpa.getNumero(),
                pedidoJpa.getStatusPedido(),
                produtoMapper.toProdutoList(pedidoJpa.getProdutos()),
                Strings.EMPTY
        );
    }
}
