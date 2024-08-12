package br.com.fiap.core.domain.model.response;

import br.com.fiap.core.domain.model.Cliente;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.Produto;

import java.util.List;

public class PedidoResponse {
    int id;
    Cliente cliente;
    String numero;
    PedidoStatus statusPedido;
    List<Produto> produtos;
    String path;

    public PedidoResponse() {
    }

    public PedidoResponse(int id, Cliente cliente, String numero, PedidoStatus statusPedido, List<Produto> produtos, String path) {
        this.id = id;
        this.cliente = cliente;
        this.numero = numero;
        this.statusPedido = statusPedido;
        this.produtos = produtos;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public PedidoStatus getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(PedidoStatus statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
