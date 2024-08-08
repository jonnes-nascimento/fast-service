package br.com.fiap.core.domain.model.request;

import br.com.fiap.core.domain.model.Cliente;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.Produto;
import br.com.fiap.core.domain.model.ProdutoCategoria;

import java.util.List;

public record PedidoUpdateRequest(
        int id,
        Cliente cliente,
        String numero,
        PedidoStatus statusPedido,
        List<Produto> produtos
) {
}