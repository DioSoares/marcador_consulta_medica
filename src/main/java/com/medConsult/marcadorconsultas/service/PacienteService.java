package com.medConsult.marcadorconsultas.service;

import com.medConsult.marcadorconsultas.dto.PacienteDTO;
import com.medConsult.marcadorconsultas.exception.DuplicateResourceException;
import com.medConsult.marcadorconsultas.model.Paciente;
import com.medConsult.marcadorconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    private Paciente toEntity(PacienteDTO dto) {
        return Paciente.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .build();
    }

    private PacienteDTO toDTO(Paciente paciente) {
        return PacienteDTO.builder()
                .id(paciente.getId())
                .nome(paciente.getNome())
                .cpf(paciente.getCpf())
                .telefone(paciente.getTelefone())
                .email(paciente.getEmail())
                .build();
    }

    @Transactional
    public PacienteDTO salvar(PacienteDTO dto) {

        if(pacienteRepository.existsByCpf(dto.getCpf())) {
            throw new DuplicateResourceException("Já existe um paciente com o CPF " + dto.getCpf());
        }
        Paciente paciente = toEntity(dto);
        Paciente salvo = pacienteRepository.save(paciente);
        return toDTO(salvo);
    }

    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
