package br.com.fiap.core.domain.model.request;

import br.com.fiap.core.domain.model.Cliente;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.Produto;

import java.util.List;

public record PedidoCreateRequest(
        Cliente cliente,
        String numero,
        PedidoStatus statusPedido,
        List<Produto> produtos
) {
}
