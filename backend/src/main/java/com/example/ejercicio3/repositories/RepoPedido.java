package com.example.ejercicio3.repositories;

import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RepoPedido extends JpaRepository<Pedido, Long>{

    List<Pedido> findByUsuario(Usuario usuario);

}
