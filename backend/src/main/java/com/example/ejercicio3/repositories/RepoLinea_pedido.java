package com.example.ejercicio3.repositories;

import java.util.List;
import com.example.ejercicio3.entities.Linea_pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ejercicio3.entities.Pedido;

@Repository
public interface RepoLinea_pedido extends JpaRepository<Linea_pedido, Long>{

    List<Linea_pedido> findByPedido(Pedido pedido);

}
