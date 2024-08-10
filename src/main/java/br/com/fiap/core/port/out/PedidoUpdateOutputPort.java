package br.com.fiap.core.port.out;

import br.com.fiap.core.domain.model.request.PedidoUpdateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;

public interface PedidoUpdateOutputPort {
    PedidoResponse update(PedidoUpdateRequest pedido);
}
