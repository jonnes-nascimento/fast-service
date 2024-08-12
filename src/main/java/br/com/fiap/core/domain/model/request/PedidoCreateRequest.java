package br.com.fiap.core.domain.model.request;

import br.com.fiap.core.domain.model.PedidoStatus;

import java.util.List;

public record PedidoCreateRequest(
        Integer cliente_id,
        String numero,
        PedidoStatus status_pedido,
        List<Integer> produtos_id
) {
}
