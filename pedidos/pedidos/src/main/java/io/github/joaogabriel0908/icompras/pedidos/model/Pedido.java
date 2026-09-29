package io.github.joaogabriel0908.icompras.pedidos.model;

import java.time.LocalDateTime;
import java.util.List;

import io.github.joaogabriel0908.icompras.pedidos.model.enums.StatusPedido;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "pedido")
@Getter 
@Setter 
@NoArgsConstructor 
public class Pedido {
    
    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long codigo;

    @Column (name = "codigo_cliente", nullable = false)
    private Long codigoCliente;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    @Column(name = "total", precision = 16, scale = 2)
    private BigDecimal total;  

    @Column(name = "chave_pagamento")
    private String chavePagamento;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "status")
    @Enumerated (EnumType.STRING)
    private StatusPedido status;

    @Column(name = "codigo_rastreio")
    private String codigoRastreio;

    @Column(name = "url_nf")
    private String url_nf;

    @Transient 
    private DadosPagamento dadosPagamento;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}
