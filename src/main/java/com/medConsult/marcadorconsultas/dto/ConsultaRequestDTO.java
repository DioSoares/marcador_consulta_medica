package com.medConsult.marcadorconsultas.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConsultaRequestDTO {

    @NotNull
    private Long medicoId;

    @NotNull
    @Future(message = "A data e hora da consulta deve ser no futuro")
    private LocalDateTime dataHora;

}
