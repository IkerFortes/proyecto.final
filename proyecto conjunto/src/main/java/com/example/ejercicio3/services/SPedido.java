package com.example.ejercicio3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.repositories.RepoPedido;
import com.example.ejercicio3.repositories.RepoUsuario;

@Service
public class SPedido {
    @Autowired RepoPedido repoPedido;
    @Autowired RepoUsuario repoUsuario;

    // public List<Pedido> todosPedidosUsuario(Long id){
    //     return repoPedido.findByUsuario(repoUsuario.findById(id).get());
    // }

    public Optional<Pedido> buscarPedido(Long id){
        return repoPedido.findById(id);
    }

    
}
