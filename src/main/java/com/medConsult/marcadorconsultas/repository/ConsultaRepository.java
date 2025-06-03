package com.medConsult.marcadorconsultas.repository;

import com.medConsult.marcadorconsultas.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndDataHora(Long medicoId, LocalDateTime dataHora);

}
