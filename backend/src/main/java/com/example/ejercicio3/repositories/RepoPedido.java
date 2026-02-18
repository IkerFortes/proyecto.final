package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoPedido extends JpaRepository<Pedido, Long>{
    // Se agrega para que SPedido.pedidosUsuario funcione
    List<Pedido> findByUsuarioId(Long usuarioId);
}
