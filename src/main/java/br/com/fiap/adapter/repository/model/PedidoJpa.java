package br.com.fiap.adapter.repository.model;

import br.com.fiap.core.domain.model.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "pedido")
@Table(uniqueConstraints = { @UniqueConstraint(name = "uk_numero", columnNames = { "numero" }) })
public class PedidoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id",
            foreignKey = @ForeignKey(name = "fk_cliente_id"))
    private ClienteJpa cliente;
    @Column(nullable = false)
    private String numero;
    private PedidoStatus statusPedido;
    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"),
            foreignKey = @ForeignKey(name = "fk_pedido_id"),
            inverseForeignKey = @ForeignKey(name = "fk_produto_id")
    )
    private List<ProdutoJpa> produtos;

    public PedidoJpa(ClienteJpa cliente, String numero, PedidoStatus statusPedido, List<ProdutoJpa> produtos) {
        this.cliente = cliente;
        this.numero = numero;
        this.statusPedido = statusPedido;
        this.produtos = produtos;
    }
}
