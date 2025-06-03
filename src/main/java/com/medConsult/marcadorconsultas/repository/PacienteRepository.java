package com.medConsult.marcadorconsultas.repository;

import com.medConsult.marcadorconsultas.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository <Paciente, Long> {

    boolean existsByCpf(String cpf);

}
