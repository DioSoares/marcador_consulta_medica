package com.medConsult.marcadorconsultas.service;

import com.medConsult.marcadorconsultas.dto.MedicoDTO;
import com.medConsult.marcadorconsultas.model.Medico;
import com.medConsult.marcadorconsultas.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    // Nesta parte é feito a conversão de DTO para entidade
    private Medico toEntity(MedicoDTO dto) {
        return Medico.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .especialidade(dto.getEspecialidade())
                .crm(dto.getCrm())
                .telefone(dto.getTelefone())
                .email(dto.getEmail())
                .build();
    }

    // Nesta parte é feito o inverso, entidade para DTO
    private MedicoDTO toDTO(Medico medico) {
        return MedicoDTO.builder()
                .id(medico.getId())
                .nome(medico.getNome())
                .especialidade(medico.getEspecialidade())
                .crm(medico.getCrm())
                .telefone(medico.getTelefone())
                .email(medico.getEmail())
                .build();
    }

    @Transactional
    public MedicoDTO salvar(MedicoDTO dto) {
        Medico medico = toEntity(dto);
        Medico salvo = medicoRepository.save(medico);
        return toDTO(salvo);
    }

    public List<MedicoDTO> listarTodos() {
        return medicoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
