package br.com.fiap.adapter.repository.model;

import br.com.fiap.core.domain.model.Cliente;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.Produto;
import jakarta.persistence.*;


import java.util.List;

@Entity(name = "pedido")
public class PedidoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Cliente cliente;
    private String numero;
    private PedidoStatus statusPedido;
    private List<Produto> produtos;

    public PedidoJpa() {
    }

    public PedidoJpa(Cliente cliente, String numero, PedidoStatus statusPedido, List<Produto> produtos) {
        this.id = null;
        this.cliente = cliente;
        this.numero = numero;
        this.statusPedido = statusPedido;
        this.produtos = produtos;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
