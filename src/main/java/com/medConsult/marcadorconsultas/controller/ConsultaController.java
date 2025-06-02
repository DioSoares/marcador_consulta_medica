package com.medConsult.marcadorconsultas.controller;

import com.medConsult.marcadorconsultas.dto.ConsultaDTO;
import com.medConsult.marcadorconsultas.service.ConsultaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @PostMapping
    public ResponseEntity<ConsultaDTO> cadastrar(@RequestBody @Valid ConsultaDTO dto) {
        ConsultaDTO consultaSalva = consultaService.salvar(dto);
        return ResponseEntity.ok(consultaSalva);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> listar() {
        return ResponseEntity.ok(consultaService.listar());
    }
}
