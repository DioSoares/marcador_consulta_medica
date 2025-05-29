package com.medConsult.marcadorconsultas.controller;

import com.medConsult.marcadorconsultas.dto.MedicoDTO;
import com.medConsult.marcadorconsultas.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    // POST - para cadastrar novo médico
    @PostMapping
    public ResponseEntity<MedicoDTO> cadastrar(@RequestBody @Valid MedicoDTO dto){
        MedicoDTO medicoSalvo = medicoService.salvar(dto);
        return ResponseEntity.ok(medicoSalvo);
    }

    // GET - para listar todos os médicos
    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listar() {
        List<MedicoDTO> medicos = medicoService.listarTodos();
        return ResponseEntity.ok(medicos);
    }
}
