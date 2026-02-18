package com.example.ejercicio3.dtos;

import java.time.LocalDateTime;

public record EntradaDTO(
    Long id,
    Long tipoEntradaId,
    Long usuarioId,
    LocalDateTime fechaCompra,
    int cantidad
) {}
