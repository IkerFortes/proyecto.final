package com.example.ejercicio3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Linea_pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name="pedidoId")
    public Pedido pedido;

    @ManyToOne
    @JoinColumn(name="productoId")
    public Producto producto;

    public int cantidad;
}
