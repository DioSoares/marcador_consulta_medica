package com.medConsult.marcadorconsultas.repository;

import com.medConsult.marcadorconsultas.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
}
