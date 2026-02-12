package com.example.ejercicio3.repositories;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Usuario;

@Repository
public interface RepoPedido extends JpaRepository<Pedido, Long>{

    List<Pedido> findByUsuario(Usuario usuario);
}
