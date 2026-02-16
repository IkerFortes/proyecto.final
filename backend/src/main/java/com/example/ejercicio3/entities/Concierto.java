package com.example.ejercicio3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Concierto {

    @Id
    public Long id;          

    public LocalDateTime fecha;
    public String nombre;
    public Long recintoId;
    public Double precio_base;
    public String estado;    
}
