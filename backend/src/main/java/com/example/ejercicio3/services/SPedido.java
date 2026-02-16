package com.example.ejercicio3.services;

import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Usuario;
import com.example.ejercicio3.repositories.RepoPedido;
import com.example.ejercicio3.repositories.RepoUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SPedido {

    @Autowired RepoPedido repoPedido;
    @Autowired RepoUsuario repoUsuario;

    public List<Pedido> pedidosUsuario(Long usuarioId){

        Usuario u = repoUsuario.findById(usuarioId).orElse(null);
        if(u == null) return List.of();

        return repoPedido.findByUsuario(u);
    }

    public Pedido crearPedido(Long usuarioId){

        Usuario u = repoUsuario.findById(usuarioId).orElse(null);
        if(u == null) return null;

        Pedido p = new Pedido();
        p.setUsuario(u);
        p.setFecha(LocalDateTime.now());

        return repoPedido.save(p);
    }

    public Optional<Pedido> buscarPedido(Long id){
        return repoPedido.findById(id);
    }

    public boolean borrarPedido(Long id){

        if(!repoPedido.existsById(id)) return false;

        repoPedido.deleteById(id);
        return true;
    }
}
