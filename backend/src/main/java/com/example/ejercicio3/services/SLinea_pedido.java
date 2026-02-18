package com.example.ejercicio3.services;

import com.example.ejercicio3.apimock.ApiMockClient;
import com.example.ejercicio3.entities.Linea_pedido;
import com.example.ejercicio3.entities.Pedido;
import com.example.ejercicio3.entities.Producto;
import com.example.ejercicio3.dtos.ConciertoDTO;
import com.example.ejercicio3.dtos.EntradaDTO;
import com.example.ejercicio3.repositories.RepoLinea_pedido;
import com.example.ejercicio3.repositories.RepoPedido;
import com.example.ejercicio3.repositories.RepoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SLinea_pedido {

    @Autowired
    private RepoLinea_pedido repoLinea_pedido;

    @Autowired
    private RepoPedido repoPedido;

    @Autowired
    private RepoProducto repoProducto;

    @Autowired
    private ApiMockClient apiMockClient;

    public List<Linea_pedido> todasLineasPedidos(Long pedidoId){
        return repoPedido.findById(pedidoId)
                .map(repoLinea_pedido::findByPedido)
                .orElse(List.of());
    }

    public Linea_pedido agregarLinea(Long pedidoId, Long productoId, int cantidad, Long usuarioId){
        if(cantidad <= 0) return null;

        var pedidoOpt = repoPedido.findById(pedidoId);
        var productoOpt = repoProducto.findById(productoId);
        if(pedidoOpt.isEmpty() || productoOpt.isEmpty()) return null;

        var pedido = pedidoOpt.get();
        var producto = productoOpt.get();

        if(!pedido.getUsuarioId().equals(usuarioId)) return null;
        if(cantidad > producto.getStock()) return null;

        ConciertoDTO concierto = apiMockClient.getConcierto(producto.getConciertoId());
        if(concierto == null || "CANCELADO".equalsIgnoreCase(concierto.estado())
           || "FINALIZADO".equalsIgnoreCase(concierto.estado())) return null;

        List<EntradaDTO> entradas = apiMockClient.entradasUsuarioConcierto(usuarioId, producto.getConciertoId());
        if(entradas.isEmpty()) return null;

        producto.setStock(producto.getStock() - cantidad);
        repoProducto.save(producto);

        Linea_pedido linea = new Linea_pedido();
        linea.setPedido(pedido);
        linea.setProductoId(productoId);
        linea.setCantidad(cantidad);

        return repoLinea_pedido.save(linea);
    }

    public Linea_pedido modificarLinea(Long lineaId, int nuevaCantidad, Long usuarioId){
        if(nuevaCantidad <= 0) return null;

        var lineaOpt = repoLinea_pedido.findById(lineaId);
        if(lineaOpt.isEmpty()) return null;

        Linea_pedido linea = lineaOpt.get();
        if(!linea.getPedido().getUsuarioId().equals(usuarioId)) return null;

        Producto producto = repoProducto.findById(linea.getProductoId()).orElse(null);
        if(producto == null) return null;

        int stockDisponible = producto.getStock() + linea.getCantidad();
        if(nuevaCantidad > stockDisponible) return null;

        ConciertoDTO concierto = apiMockClient.getConcierto(producto.getConciertoId());
        if(concierto == null || "CANCELADO".equalsIgnoreCase(concierto.estado())
           || "FINALIZADO".equalsIgnoreCase(concierto.estado())) return null;

        List<EntradaDTO> entradas = apiMockClient.entradasUsuarioConcierto(usuarioId, producto.getConciertoId());
        if(entradas.isEmpty()) return null;

        producto.setStock(stockDisponible - nuevaCantidad);
        repoProducto.save(producto);

        linea.setCantidad(nuevaCantidad);
        return repoLinea_pedido.save(linea);
    }

    public boolean eliminarLinea(Long lineaId, Long usuarioId){
        var lineaOpt = repoLinea_pedido.findById(lineaId);
        if(lineaOpt.isEmpty()) return false;

        Linea_pedido linea = lineaOpt.get();
        if(!linea.getPedido().getUsuarioId().equals(usuarioId)) return false;

        Producto producto = repoProducto.findById(linea.getProductoId()).orElse(null);
        if(producto != null){
            producto.setStock(producto.getStock() + linea.getCantidad());
            repoProducto.save(producto);
        }

        repoLinea_pedido.delete(linea);
        return true;
    }
}
