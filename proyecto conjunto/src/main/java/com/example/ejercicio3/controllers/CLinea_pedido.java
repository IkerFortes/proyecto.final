package com.example.ejercicio3.controllers;
import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.services.SLinea_pedido;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class CLinea_pedido {
    @Autowired SLinea_pedido servicio;

    @GetMapping("/pedidos/{id}/lineas")
    public ResponseEntity<List<Linea_pedido>> todasLineasPedido(@PathVariable Long id) {
        return ResponseEntity.ok().body(servicio.todasLineasPedidos(id));
    }
    
}
