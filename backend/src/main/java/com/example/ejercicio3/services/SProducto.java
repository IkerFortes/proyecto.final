package com.example.ejercicio3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.repositories.RepoProducto;

@Service
public class SProducto {
    
    @Autowired RepoProducto repoProducto;

    public List<Producto> todosProductos(){
        return  repoProducto.findAll();
    }

    // public Producto productoPorId(Long prodId){
    //     return repoProducto.find(prodId);
    // }

    public Producto insertarProducto(Producto nuevo){
        // if(repoProducto.existsById(nuevo.getId())){
        //     return null;
        // }
        return repoProducto.save(nuevo);
    }

    public Producto actualizarProducto(Producto upProducto, Long id){
        if(!repoProducto.existsById(id)){
            return null;
        }
        Producto prodBD = repoProducto.findById(id).get();
        prodBD.setConciertoId(upProducto.getConciertoId());
        repoProducto.save(prodBD);
        return prodBD;
    }

    public boolean borrarProducto(Long id){
        if(!repoProducto.existsById(id)){
            return false;
        }
        repoProducto.delete(repoProducto.findById(id).get());
        return true;
    }
}
