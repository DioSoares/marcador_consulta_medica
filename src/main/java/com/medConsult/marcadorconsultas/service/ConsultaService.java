package com.medConsult.marcadorconsultas.service;

import com.medConsult.marcadorconsultas.dto.ConsultaDTO;
import com.medConsult.marcadorconsultas.model.Consulta;
import com.medConsult.marcadorconsultas.model.Medico;
import com.medConsult.marcadorconsultas.model.Paciente;
import com.medConsult.marcadorconsultas.repository.ConsultaRepository;
import com.medConsult.marcadorconsultas.repository.MedicoRepository;
import com.medConsult.marcadorconsultas.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    @Transactional
    public ConsultaDTO salvar(ConsultaDTO dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(()-> new RuntimeException("Paciente não encontrado."));

        Consulta consulta = Consulta.builder()
                .medico(medico)
                .paciente(paciente)
                .dataHora(dto.getDataHora())
                .observacoes(dto.getObservacoes())
                .build();

        Consulta salva = consultaRepository.save(consulta);

        return toDTO(salva);
    }

    public List<ConsultaDTO> listar() {
        return consultaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ConsultaDTO toDTO(Consulta consulta) {
        return ConsultaDTO.builder()
                .id(consulta.getId())
                .medicoId(consulta.getMedico().getId())
                .nomeMedico(consulta.getMedico().getNome())
                .pacienteId(consulta.getPaciente().getId())
                .nomePaciente(consulta.getPaciente().getNome())
                .dataHora(consulta.getDataHora())
                .observacoes(consulta.getObservacoes())
                .build();
    }
}
