package com.example.ejercicio3.controllers;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.services.SPedido;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/pedidos")
public class CPedido {
    
    @Autowired SPedido servicio;

    @GetMapping("/usuario/{id}")
    public String getMethodName(@RequestParam Long id) {
        return new String();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pedido>> buscarPedido(@PathVariable Long id) {
        return ResponseEntity.ok().body(servicio.buscarPedido(id));
    }
    
    
}
