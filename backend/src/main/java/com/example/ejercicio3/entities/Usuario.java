package com.example.ejercicio3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    public Long id;
    public String nombre;
    public String email;
    public String rol;
}
