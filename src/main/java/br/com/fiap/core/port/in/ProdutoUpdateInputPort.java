package br.com.fiap.core.port.in;

import br.com.fiap.core.domain.model.request.ProdutoUpdateRequest;
import br.com.fiap.core.domain.model.response.ProdutoResponse;

public interface ProdutoUpdateInputPort {
    ProdutoResponse update(ProdutoUpdateRequest produtoUpdateRequest);
}
