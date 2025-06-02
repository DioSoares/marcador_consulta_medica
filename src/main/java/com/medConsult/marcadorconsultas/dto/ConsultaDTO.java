package com.medConsult.marcadorconsultas.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ConsultaDTO {

    private Long id;

    private Long medicoId;
    private String nomeMedico;

    private Long pacienteId;
    private String nomePaciente;

    private LocalDateTime dataHora;

    private String observacoes;
}
