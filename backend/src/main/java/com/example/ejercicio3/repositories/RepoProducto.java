package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoProducto extends JpaRepository<Producto,Long>{

    List<Producto> findByConciertoId(Long conciertoId);

}
