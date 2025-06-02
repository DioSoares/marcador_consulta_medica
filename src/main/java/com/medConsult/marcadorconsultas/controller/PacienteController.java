package com.medConsult.marcadorconsultas.controller;

import com.medConsult.marcadorconsultas.dto.PacienteDTO;
import com.medConsult.marcadorconsultas.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<PacienteDTO> cadastrar(@RequestBody @Valid PacienteDTO dto) {
        PacienteDTO pacienteSalvo = pacienteService.salvar(dto);
        return ResponseEntity.ok(pacienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTO>> listar () {
        List<PacienteDTO> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }
}
