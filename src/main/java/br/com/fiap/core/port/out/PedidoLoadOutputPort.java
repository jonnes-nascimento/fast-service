package br.com.fiap.core.port.out;

import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.response.PedidoResponse;

import java.util.List;

public interface PedidoLoadOutputPort {
    PedidoResponse loadByNumero(String numero);

    List<PedidoResponse> loadAll();

    List<PedidoResponse> loadByStatus(PedidoStatus status);
}
