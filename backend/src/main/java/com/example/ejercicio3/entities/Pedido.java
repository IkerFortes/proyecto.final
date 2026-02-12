package com.example.ejercicio3.entities;

import java.time.LocalDateTime;

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
public class Pedido {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn (name="usuarioId")
    public Usuario usuario;
    
    public LocalDateTime fecha;
}
