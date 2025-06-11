package com.medConsult.marcadorconsultas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsultaCancelamentoDTO {

    @NotNull
    private Long consultaId;
}
