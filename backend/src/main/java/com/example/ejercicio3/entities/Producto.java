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

    public String nombre;
    public Double precio;
    public int stock;

    @ManyToOne
    @JoinColumn(name="conciertoId")
    public Concierto concierto;   
}
