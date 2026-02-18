package com.example.ejercicio3.controllers;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.services.SProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class CProducto {

    @Autowired
    private SProducto servicio;

    @Autowired
    private ApiMockClient apiMockClient;

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
        if(!isAdmin(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Producto creado = servicio.insertarProducto(nuevo);
        if(creado == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editar(@PathVariable Long id,
                                           @RequestBody Producto p,
                                           @RequestParam Long usuarioId) {
        if(!isAdmin(usuarioId))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Producto act = servicio.actualizarProducto(p, id);
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

    // Ahora usamos ApiMockClient para validar rol
    private boolean isAdmin(Long usuarioId){
        var usuario = apiMockClient.getUsuario(usuarioId);
        return usuario != null && "ADMIN".equalsIgnoreCase(usuario.rol());
    }
}
