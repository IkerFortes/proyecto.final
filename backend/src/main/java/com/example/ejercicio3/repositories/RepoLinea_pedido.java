package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoLinea_pedido extends JpaRepository<Linea_pedido, Long> {
    List<Linea_pedido> findByPedido(Pedido pedido);
}
