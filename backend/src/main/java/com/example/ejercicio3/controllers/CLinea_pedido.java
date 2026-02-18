package com.example.ejercicio3.controllers;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.services.SLinea_pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CLinea_pedido {

    @Autowired
    private SLinea_pedido servicio;

    @Autowired
    private ApiMockClient apiMockClient;

    @GetMapping("/pedidos/{id}/lineas")
    public ResponseEntity<List<Linea_pedido>> todasLineasPedido(@PathVariable Long id){
        return ResponseEntity.ok(servicio.todasLineasPedidos(id));
    }

    @PostMapping("/pedidos/{pedidoId}/lineas/{productoId}")
    public ResponseEntity<Linea_pedido> agregarLinea(
            @PathVariable Long pedidoId,
            @PathVariable Long productoId,
            @RequestParam int cantidad,
            @RequestParam Long usuarioId) {

        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Linea_pedido linea = servicio.agregarLinea(pedidoId, productoId, cantidad, usuarioId);
        if(linea == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(linea);
    }

    @PutMapping("/lineas/{lineaId}")
    public ResponseEntity<Linea_pedido> modificarLinea(
            @PathVariable Long lineaId,
            @RequestParam int cantidad,
            @RequestParam Long usuarioId) {

        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Linea_pedido linea = servicio.modificarLinea(lineaId, cantidad, usuarioId);
        if(linea == null)
            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        return ResponseEntity.ok(linea);
    }

    @DeleteMapping("/lineas/{lineaId}")
    public ResponseEntity<Void> eliminarLinea(@PathVariable Long lineaId,
                                              @RequestParam Long usuarioId){

        if(!isCliente(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        boolean ok = servicio.eliminarLinea(lineaId, usuarioId);
        if(!ok) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.noContent().build();
    }

    private boolean isCliente(Long usuarioId){
        var usuario = apiMockClient.getUsuario(usuarioId);
        return usuario != null && "CLIENTE".equalsIgnoreCase(usuario.rol());
    }
}
