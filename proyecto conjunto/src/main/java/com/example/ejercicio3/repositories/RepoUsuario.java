package com.example.ejercicio3.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.entities.Usuario;

@Repository
public interface RepoUsuario extends JpaRepository<Usuario,Long>{
    Optional<Usuario> findById(Long id);
}
