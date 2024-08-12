package br.com.fiap.core.domain.model;

import java.util.List;
import java.util.Objects;

public class Pedido {

    private Cliente cliente;
    private String numero;
    private PedidoStatus statusPedido;
    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Cliente cliente, String numero, PedidoStatus statusPedido, List<Produto> produtos) {
        this.cliente = cliente;
        this.numero = numero;
        this.statusPedido = statusPedido;
        this.produtos = produtos;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(getNumero(), pedido.getNumero());
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", numero='" + numero + '\'' +
                ", statusPedido=" + statusPedido +
                ", produtos=" + produtos +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero());
    }
}
