package br.com.fiap.core.usecase;

import br.com.fiap.core.domain.model.Pedido;
import br.com.fiap.core.domain.model.request.PedidoCreateRequest;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.port.in.PedidoCreateInputPort;
import br.com.fiap.core.port.out.PedidoSaveOutputPort;

public class PedidoCreateUseCase implements PedidoCreateInputPort {

    private final PedidoSaveOutputPort pedidoSaveOutputPort;


    public PedidoCreateUseCase(PedidoSaveOutputPort pedidoSaveOutputPort) {
        this.pedidoSaveOutputPort = pedidoSaveOutputPort;
    }

    @Override
    public PedidoResponse create(PedidoCreateRequest pedidoCreateRequest) {
        return pedidoSaveOutputPort.save(pedidoCreateRequest);
    }
}
