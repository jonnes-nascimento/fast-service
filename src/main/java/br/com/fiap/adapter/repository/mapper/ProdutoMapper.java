package br.com.fiap.adapter.repository.mapper;

import br.com.fiap.adapter.repository.model.ProdutoJpa;
import br.com.fiap.core.domain.model.Produto;
import br.com.fiap.core.domain.model.response.ProdutoResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoMapper {

    public ProdutoJpa toProdutoJpa(Produto produto) {
        return new ProdutoJpa(
                produto.getCategoria(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        );
    }

    public Produto toProduto(ProdutoJpa produtoJpa) {
        return new Produto(
                produtoJpa.getCategoria(),
                produtoJpa.getNome(),
                produtoJpa.getDescricao(),
                produtoJpa.getPreco()
        );
    }

    public ProdutoResponse toProdutoResponse(ProdutoJpa produtoJpa) {
        return new ProdutoResponse(
                produtoJpa.getId(),
                produtoJpa.getCategoria(),
                produtoJpa.getNome(),
                produtoJpa.getDescricao(),
                produtoJpa.getPreco(),
                Strings.EMPTY
        );
    }

    public List<Produto> toProdutoList(List<ProdutoJpa> produtoJpaList) {
        return produtoJpaList.stream()
                .map(this::toProduto)
                .toList();
    }

    public List<ProdutoJpa> toProdutoJpaList(List<Produto> produtoList) {
        return produtoList.stream()
                .map(this::toProdutoJpa)
                .toList();
    }
}
