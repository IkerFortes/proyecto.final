package com.example.ejercicio3.apimock;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ejercicio3.dtos.ConciertoDTO;
import com.example.ejercicio3.dtos.EntradaDTO;
import com.example.ejercicio3.dtos.UsuarioDTO;

@Service
public class ApiMockClient {

    @Autowired
    private RestTemplate rest;

    private final String BASE = "http://localhost:8080/api"; // Ajusta el puerto donde corre el mock

    public UsuarioDTO getUsuario(Long id){
        try {
            return rest.getForObject(BASE + "/usuarios/" + id, UsuarioDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    public ConciertoDTO getConcierto(Long id){
        try {
            return rest.getForObject(BASE + "/conciertos/" + id, ConciertoDTO.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<EntradaDTO> entradasUsuarioConcierto(Long usuarioId, Long conciertoId){
        try {
            EntradaDTO[] arr = rest.getForObject(
                BASE + "/entradas/usuario/" + usuarioId + "/concierto/" + conciertoId,
                EntradaDTO[].class
            );
            return arr == null ? List.of() : Arrays.asList(arr);
        } catch (Exception e) {
            return List.of();
        }
    }
}
