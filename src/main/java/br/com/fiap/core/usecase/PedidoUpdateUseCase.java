package br.com.fiap.core.usecase;

import br.com.fiap.core.domain.model.request.PedidoUpdateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.port.in.PedidoUpdateInputPort;
import br.com.fiap.core.port.out.PedidoUpdateOutputPort;

public class PedidoUpdateUseCase implements PedidoUpdateInputPort {

    private final PedidoUpdateOutputPort pedidoUpdateOutputPort;

    public PedidoUpdateUseCase(PedidoUpdateOutputPort pedidoUpdateOutputPort) {
        this.pedidoUpdateOutputPort = pedidoUpdateOutputPort;
    }

    @Override
    public PedidoResponse update(PedidoUpdateRequest pedidoUpdateRequest) {
        return pedidoUpdateOutputPort.update(pedidoUpdateRequest);
    }
}
