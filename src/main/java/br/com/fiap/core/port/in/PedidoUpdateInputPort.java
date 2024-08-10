package br.com.fiap.core.port.in;

import br.com.fiap.core.domain.model.request.PedidoUpdateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;

public interface PedidoUpdateInputPort {
    PedidoResponse update(PedidoUpdateRequest pedidoUpdateRequest);
}
