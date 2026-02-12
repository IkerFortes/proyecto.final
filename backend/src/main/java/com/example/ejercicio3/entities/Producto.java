package com.example.ejercicio3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long id;

    public String nombre;
    public Double precio;
    public int stock;

    // @ManyToOne
    // @JoinColumn(name="conciertoId")
    public Long conciertoId;
}
