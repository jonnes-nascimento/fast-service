package br.com.fiap.core.port.out;

import br.com.fiap.core.domain.model.Pedido;
import br.com.fiap.core.domain.model.response.PedidoResponse;

public interface PedidoSaveOutputPort {
    PedidoResponse save(Pedido pedido);
}
