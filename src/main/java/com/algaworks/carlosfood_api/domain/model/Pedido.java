package com.algaworks.carlosfood_api.domain.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valoTotal;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;

    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @Embedded
    private Endereco enderecoEntrega;

}
