package br.com.fiap.core.port.in;

import br.com.fiap.core.domain.model.response.PedidoResponse;

import java.util.List;

public interface PedidoLoadInputPort {
    PedidoResponse loadById(int id);

    List<PedidoResponse> loadAll();
}
