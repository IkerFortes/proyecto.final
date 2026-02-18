package com.example.ejercicio3.services;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.repositories.RepoPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SPedido {

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private ApiMockClient apiMockClient;

    public List<Pedido> pedidosUsuario(Long usuarioId){
        if(apiMockClient.getUsuario(usuarioId) == null) return List.of();
        return repoPedido.findByUsuarioId(usuarioId);
    }

    public Pedido crearPedido(Long usuarioId){
        if(apiMockClient.getUsuario(usuarioId) == null) return null;
        Pedido p = new Pedido();
        p.setUsuarioId(usuarioId);
        p.setFecha(LocalDateTime.now());
        return repoPedido.save(p);
    }

    public Optional<Pedido> buscarPedido(Long id){
        return repoPedido.findById(id);
    }

    public Pedido actualizarPedido(Pedido pedido){
        return repoPedido.save(pedido);
    }

    public boolean borrarPedido(Long id){
        if(!repoPedido.existsById(id)) return false;
        repoPedido.deleteById(id);
        return true;
    }
}
