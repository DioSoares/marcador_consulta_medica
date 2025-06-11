package com.medConsult.marcadorconsultas.controller;

import com.medConsult.marcadorconsultas.dto.ConsultaCancelamentoDTO;
import com.medConsult.marcadorconsultas.dto.ConsultaRequestDTO;
import com.medConsult.marcadorconsultas.dto.ConsultaResponseDTO;
import com.medConsult.marcadorconsultas.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaResponseDTO> agendarConsulta(@RequestBody @Valid ConsultaRequestDTO dto,
                                                               Authentication authentication) {
        return ResponseEntity.ok(consultaService.agendarConsulta(dto, authentication));
    }

    @GetMapping
    public ResponseEntity<List<ConsultaResponseDTO>> listarConsultas() {
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @PutMapping("/cancelar")
    public ResponseEntity<Void> cancelarConsulta(@RequestBody @Valid ConsultaCancelamentoDTO dto) {
        consultaService.cancelarConsulta(dto);
        return ResponseEntity.noContent().build();
    }
}
