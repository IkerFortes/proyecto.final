package com.example.ejercicio3.services;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.dtos.ConciertoDTO;
import com.example.ejercicio3.repositories.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SProducto {

    @Autowired
    private RepoProducto repoProducto;

    @Autowired
    private ApiMockClient apiMockClient;

    public List<Producto> todosProductos() {
        return repoProducto.findAll();
    }

    public Producto productoPorId(Long id){
        return repoProducto.findById(id).orElse(null);
    }

    public Producto insertarProducto(Producto nuevo) {
        // Validar que el concierto exista y no esté cancelado
        ConciertoDTO c = apiMockClient.getConcierto(nuevo.getConciertoId());
        if(c == null || "CANCELADO".equalsIgnoreCase(c.estado()))
            return null;

        return repoProducto.save(nuevo);
    }

    public Producto actualizarProducto(Producto upProducto, Long id) {
        var prodBD = repoProducto.findById(id).orElse(null);
        if(prodBD == null) return null;

        // Validar concierto existente y no cancelado
        ConciertoDTO c = apiMockClient.getConcierto(upProducto.getConciertoId());
        if(c == null || "CANCELADO".equalsIgnoreCase(c.estado())) return null;

        prodBD.setNombre(upProducto.getNombre());
        prodBD.setPrecio(upProducto.getPrecio());
        prodBD.setStock(upProducto.getStock());
        prodBD.setConciertoId(upProducto.getConciertoId());

        return repoProducto.save(prodBD);
    }

    public boolean borrarProducto(Long id){
        if(!repoProducto.existsById(id)) return false;
        repoProducto.deleteById(id);
        return true;
    }
}
