package com.example.ejercicio3.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio3.entities.Producto;

@Repository
public interface RepoProducto extends JpaRepository<Producto,Long>{
    // Producto find(Long concId);
}
