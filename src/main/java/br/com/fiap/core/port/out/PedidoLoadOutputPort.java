package br.com.fiap.core.port.out;

import br.com.fiap.core.domain.model.response.PedidoResponse;

import java.util.List;

public interface PedidoLoadOutputPort {
    PedidoResponse loadById(int id);

    List<PedidoResponse> loadAll();
}
