package com.example.ejercicio3.services;

import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.repositories.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SProducto {

    @Autowired
    RepoProducto repoProducto;

    public List<Producto> todosProductos(){
        return repoProducto.findAll();
    }

    public Producto productoPorId(Long id){
        return repoProducto.findById(id).orElse(null);
    }

    public Producto insertarProducto(Producto nuevo){
        return repoProducto.save(nuevo);
    }

    public Producto actualizarProducto(Producto upProducto, Long id){
        if(!repoProducto.existsById(id)) return null;

        Producto prodBD = repoProducto.findById(id).get();
        prodBD.setNombre(upProducto.getNombre());
        prodBD.setPrecio(upProducto.getPrecio());
        prodBD.setStock(upProducto.getStock());
        prodBD.setConcierto(upProducto.getConcierto()); 
        return repoProducto.save(prodBD);
    }

    public boolean borrarProducto(Long id){
        if(!repoProducto.existsById(id)) return false;

        repoProducto.deleteById(id);
        return true;
    }
}


