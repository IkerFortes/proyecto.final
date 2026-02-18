package com.example.ejercicio3.controllers;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.services.SPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class CPedido {

    @Autowired
    private SPedido servicio;

    @Autowired
    private ApiMockClient apiMockClient;

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Pedido>> pedidosUsuario(@PathVariable Long id){
        if(!isCliente(id))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok(servicio.pedidosUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        Optional<Pedido> pedidoOpt = servicio.buscarPedido(id);
        if(pedidoOpt.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedidoOpt.get());
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestParam Long usuarioId){
        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Pedido p = servicio.crearPedido(usuarioId);
        if(p == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(
            @PathVariable Long id,
            @RequestParam Long usuarioId,
            @RequestParam(required = false) LocalDateTime nuevaFecha) {

        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Optional<Pedido> pedidoOpt = servicio.buscarPedido(id);
        if(pedidoOpt.isEmpty()) return ResponseEntity.notFound().build();

        Pedido pedido = pedidoOpt.get();
        if(!pedido.getUsuarioId().equals(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(nuevaFecha != null) pedido.setFecha(nuevaFecha);

        Pedido actualizado = servicio.actualizarPedido(pedido);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id,
                                       @RequestParam Long usuarioId){
        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Optional<Pedido> pedidoOpt = servicio.buscarPedido(id);
        if(pedidoOpt.isEmpty() || !pedidoOpt.get().getUsuarioId().equals(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(!servicio.borrarPedido(id))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    private boolean isCliente(Long usuarioId){
        var usuario = apiMockClient.getUsuario(usuarioId);
        return usuario != null && "CLIENTE".equalsIgnoreCase(usuario.rol());
    }
}
