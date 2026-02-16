package com.example.ejercicio3.controllers;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.repositories.RepoUsuario;
import com.example.ejercicio3.services.SPedido;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/pedidos")
public class CPedido {

    @Autowired SPedido servicio;
    @Autowired RepoUsuario repoUsuario;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pedido>> pedidosUsuario(@PathVariable Long id){
        return ResponseEntity.ok(servicio.pedidosUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        return servicio.buscarPedido(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestParam Long usuarioId){
        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Pedido p = servicio.crearPedido(usuarioId);
        if(p == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id,
                                       @RequestParam Long usuarioId){
        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Optional<Pedido> pedidoOpt = servicio.buscarPedido(id);
        if(pedidoOpt.isEmpty() || !pedidoOpt.get().getUsuario().getId().equals(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(!servicio.borrarPedido(id))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    private boolean isCliente(Long usuarioId){
        return repoUsuario.findById(usuarioId)
                .map(u -> "CLIENTE".equalsIgnoreCase(u.getRol()))
                .orElse(false);
    }
}
