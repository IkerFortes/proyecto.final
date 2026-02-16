package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoUsuario extends JpaRepository<Usuario, Long> {
}
