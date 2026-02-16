package com.example.ejercicio3.controllers;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.repositories.RepoUsuario;
import com.example.ejercicio3.services.SProducto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/productos")
public class CProducto {

    @Autowired SProducto servicio;

    @Autowired RepoUsuario repoUsuario;

    @GetMapping
    public ResponseEntity<List<Producto>> todosProductos(){
        return ResponseEntity.ok(servicio.todosProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> uno(@PathVariable Long id){
        Producto p = servicio.productoPorId(id);
        if(p == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Producto> insertar(@RequestBody Producto nuevo,
                                             @RequestParam Long usuarioId) {
        // Validar rol ADMIN
        if(!isAdmin(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicio.insertarProducto(nuevo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable Long id,
                                           @RequestBody Producto p,
                                           @RequestParam Long usuarioId) {
        if(!isAdmin(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Producto act = servicio.actualizarProducto(p,id);
        if(act == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(act);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id,
                                       @RequestParam Long usuarioId){
        if(!isAdmin(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        if(!servicio.borrarProducto(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.noContent().build();
    }

    // Método helper para validar rol
    private boolean isAdmin(Long usuarioId){
        return repoUsuario.findById(usuarioId)
                .map(u -> "ADMIN".equalsIgnoreCase(u.getRol()))
                .orElse(false);
    }
}
