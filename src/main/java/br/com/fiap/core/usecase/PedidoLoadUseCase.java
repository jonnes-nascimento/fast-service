package br.com.fiap.core.usecase;

import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.port.in.PedidoLoadInputPort;
import br.com.fiap.core.port.out.PedidoLoadOutputPort;

import java.util.List;

public class PedidoLoadUseCase implements PedidoLoadInputPort {

    private final PedidoLoadOutputPort pedidoLoadOutputPort;

    public PedidoLoadUseCase(PedidoLoadOutputPort pedidoLoadOutputPort) {
        this.pedidoLoadOutputPort = pedidoLoadOutputPort;
    }

    @Override
    public PedidoResponse loadById(int id) {
        return pedidoLoadOutputPort.loadById(id);
    }

    @Override
    public List<PedidoResponse> loadAll() {
        return pedidoLoadOutputPort.loadAll();
    }
}
