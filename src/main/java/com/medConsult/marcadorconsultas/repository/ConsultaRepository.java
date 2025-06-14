package com.medConsult.marcadorconsultas.repository;


import com.medConsult.marcadorconsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);
}
