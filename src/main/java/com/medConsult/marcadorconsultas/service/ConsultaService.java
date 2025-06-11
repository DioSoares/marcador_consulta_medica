package com.medConsult.marcadorconsultas.service;

import com.medConsult.marcadorconsultas.dto.ConsultaCancelamentoDTO;
import com.medConsult.marcadorconsultas.dto.ConsultaRequestDTO;
import com.medConsult.marcadorconsultas.dto.ConsultaResponseDTO;
import com.medConsult.marcadorconsultas.exception.ConflictException;
import com.medConsult.marcadorconsultas.exception.ResourceNotFoundException;
import com.medConsult.marcadorconsultas.model.Consulta;
import com.medConsult.marcadorconsultas.model.Perfil;
import com.medConsult.marcadorconsultas.model.Usuario;
import com.medConsult.marcadorconsultas.repository.ConsultaRepository;
import com.medConsult.marcadorconsultas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioRepository usuarioRepository;

    public ConsultaResponseDTO agendarConsulta(ConsultaRequestDTO dto, Authentication auth) {
        String email = auth.getName();
        Usuario paciente = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

        if (!paciente.getPerfil().equals(Perfil.PACIENTE)) {
            throw new ConflictException("Somente pacientes podem agendar consultas");
        }

        Usuario medico = usuarioRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));

        if (!medico.getPerfil().equals(Perfil.MEDICO)) {
            throw new ConflictException("O usuário informado não é um médico");
        }

        if (consultaRepository.existsByMedicoIdAndDataHora(medico.getId(), dto.getDataHora())) {
            throw new ConflictException("Médico já possui consulta nesse horário");
        }

        Consulta consulta = Consulta.builder()
                .paciente(paciente)
                .medico(medico)
                .dataHora(dto.getDataHora())
                .build();

        Consulta salva = consultaRepository.save(consulta);

        return ConsultaResponseDTO.builder()
                .id(salva.getId())
                .paciente(paciente.getNome())
                .medico(medico.getNome())
                .dataHora(salva.getDataHora().toString())
                .cancelada(salva.getCancelada())
                .build();
    }

    public List<ConsultaResponseDTO> listarConsultas() {
        return consultaRepository.findAll().stream()
                .map(c -> ConsultaResponseDTO.builder()
                        .id(c.getId())
                        .paciente(c.getPaciente().getNome())
                        .medico(c.getMedico().getNome())
                        .dataHora(c.getDataHora().toString())
                        .cancelada(c.getCancelada())
                        .build())
                .collect(Collectors.toList());
    }

    public void cancelarConsulta(ConsultaCancelamentoDTO dto) {
        Consulta consulta = consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        consulta.setCancelada(true);
        consultaRepository.save(consulta);
    }
}
