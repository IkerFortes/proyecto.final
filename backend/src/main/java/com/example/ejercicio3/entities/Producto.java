package com.example.ejercicio3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    
    private String nombre;
    private Double precio;
    private int stock;
    private Long conciertoId;   
}
