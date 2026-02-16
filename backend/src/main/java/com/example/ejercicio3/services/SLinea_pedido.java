package com.example.ejercicio3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.entities.Entrada;
import com.example.ejercicio3.repositories.RepoLinea_pedido;
import com.example.ejercicio3.repositories.RepoPedido;
import com.example.ejercicio3.repositories.RepoProducto;
import com.example.ejercicio3.repositories.RepoEntrada;

@Service
public class SLinea_pedido {

    @Autowired
    RepoLinea_pedido repoLinea_pedido;

    @Autowired
    RepoPedido repoPedido;

    @Autowired
    RepoProducto repoProducto;

    @Autowired
    RepoEntrada repoEntrada;

    public List<Linea_pedido> todasLineasPedidos(Long id){
        Optional<Pedido> pedido = repoPedido.findById(id);
        return pedido.map(p -> repoLinea_pedido.findByPedido(p)).orElse(List.of());
    }

    public Linea_pedido agregarLinea(Long pedidoId, Long productoId, int cantidad) {
        Optional<Pedido> pedidoOpt = repoPedido.findById(pedidoId);
        Optional<Producto> productoOpt = repoProducto.findById(productoId);

        if(pedidoOpt.isEmpty() || productoOpt.isEmpty()) return null;

        Pedido pedido = pedidoOpt.get();
        Producto prod = productoOpt.get();


        if(cantidad > prod.getStock()) return null;

        if("CANCELADO".equalsIgnoreCase(prod.getConcierto().getEstado())) return null;

        List<Entrada> entradas = repoEntrada.findByUsuarioIdAndConciertoId(
                pedido.getUsuario().getId(), prod.getConcierto().getId()
        );
        if(entradas.isEmpty()) 
            return null;

        Linea_pedido linea = new Linea_pedido();
        linea.setPedido(pedido);
        linea.setProducto(prod);
        linea.setCantidad(cantidad);

        prod.setStock(prod.getStock() - cantidad);
        repoProducto.save(prod);

        return repoLinea_pedido.save(linea);
    }

    public Linea_pedido modificarLinea(Long lineaId, int nuevaCantidad, Long usuarioId) {
        Optional<Linea_pedido> lineaOpt = repoLinea_pedido.findById(lineaId);
        if(lineaOpt.isEmpty()) return null;

        Linea_pedido linea = lineaOpt.get();
        if(!linea.getPedido().getUsuario().getId().equals(usuarioId)) 
            return null;

        Producto prod = linea.getProducto();

        int stockDisponible = prod.getStock() + linea.getCantidad();
        if(nuevaCantidad > stockDisponible) 
            return null;

        prod.setStock(stockDisponible - nuevaCantidad);
        repoProducto.save(prod);

        linea.setCantidad(nuevaCantidad);
        return repoLinea_pedido.save(linea);
    }

    public boolean eliminarLinea(Long lineaId, Long usuarioId){
        Optional<Linea_pedido> lineaOpt = repoLinea_pedido.findById(lineaId);
        if(lineaOpt.isEmpty()) 
            return false;

        Linea_pedido linea = lineaOpt.get();
        if(!linea.getPedido().getUsuario().getId().equals(usuarioId)) 
            return false;

        Producto prod = linea.getProducto();
        prod.setStock(prod.getStock() + linea.getCantidad());
        repoProducto.save(prod);

        repoLinea_pedido.delete(linea);
        return true;
    }
}
