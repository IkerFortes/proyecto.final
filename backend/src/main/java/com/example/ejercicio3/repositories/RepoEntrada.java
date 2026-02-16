package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepoEntrada extends JpaRepository<Entrada, Long> {

    List<Entrada> findByUsuarioIdAndConciertoId(Long usuarioId, Long conciertoId);
}
