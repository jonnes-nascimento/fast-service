package br.com.fiap.adapter.repository.mapper;

import br.com.fiap.adapter.repository.model.PedidoJpa;
import br.com.fiap.core.domain.model.Pedido;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import org.apache.logging.log4j.util.Strings;

public class PedidoMapper {
    public PedidoJpa toPedidoJpa(Pedido pedido) {
        return new PedidoJpa(
                pedido.getCliente(),
                pedido.getNumero(),
                pedido.getStatusPedido(),
                pedido.getProdutos()
        );
    }

    public Pedido toPedido(PedidoJpa pedidoJpa) {
        return new Pedido(
                pedidoJpa.getCliente(),
                pedidoJpa.getNumero(),
                pedidoJpa.getStatusPedido(),
                pedidoJpa.getProdutos()
        );
    }

    public PedidoResponse toPedidoResponse(PedidoJpa pedidoJpa) {
        return new PedidoResponse(
                pedidoJpa.getId(),
                pedidoJpa.getCliente(),
                pedidoJpa.getNumero(),
                pedidoJpa.getStatusPedido(),
                pedidoJpa.getProdutos(),
                Strings.EMPTY
        );
    }
}
