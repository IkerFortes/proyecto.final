package com.example.ejercicio3.controllers;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.services.SProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/productos")
public class CProducto {
    @Autowired SProducto servicio;

    @GetMapping
    public ResponseEntity<List<Producto>> todosProductos() {
        return ResponseEntity.ok().body(servicio.todosProductos());
    }

    // @GetMapping("/{concId}")
    // public ResponseEntity<Producto> productoPorId(@RequestParam Long prodId) {
    //     return ResponseEntity.ok().body(servicio.productoPorId(prodId));
    // }
    
    @PostMapping
    public ResponseEntity<Producto> insertarProducto(@RequestBody Producto nuevo) {
        nuevo=servicio.insertarProducto(nuevo);
        if(nuevo==null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }
    
    @PutMapping("/{id}")
    public String editarProducto(@PathVariable Long id, @RequestBody Producto upProducto) {
        
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable Long id){
        boolean borrar = servicio.borrarProducto(id);
        if(!borrar){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
