package com.example.ejercicio3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.repositories.RepoLinea_pedido;
import com.example.ejercicio3.repositories.RepoPedido;

@Service
public class SLinea_pedido {

    @Autowired RepoLinea_pedido repoLinea_pedido;
    @Autowired RepoPedido repoPedido;

    public List<Linea_pedido> todasLineasPedidos(Long id){
        return repoLinea_pedido.findByPedido(repoPedido.findById(id).get());
    }
}
