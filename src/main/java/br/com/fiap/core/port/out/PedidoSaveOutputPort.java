package br.com.fiap.core.port.out;

import br.com.fiap.core.domain.model.request.PedidoCreateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;

public interface PedidoSaveOutputPort {
    PedidoResponse save(PedidoCreateRequest pedidoCreateRequest);
}
