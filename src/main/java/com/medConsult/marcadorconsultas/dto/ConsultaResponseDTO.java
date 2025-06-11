package com.medConsult.marcadorconsultas.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsultaResponseDTO {

    private Long id;
    private String medico;
    private String paciente;
    private String dataHora;
    private Boolean cancelada;

    public static ConsultaResponseDTO from(ConsultaRequestDTO request, String medico, String paciente) {
        return ConsultaResponseDTO.builder()
                .medico(medico)
                .paciente(paciente)
                .dataHora(request.getDataHora().toString())
                .build();
    }
}
