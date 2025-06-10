package com.medConsult.marcadorconsultas.repository;

import com.medConsult.marcadorconsultas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);

    Optional<Usuario> findByid(Long id);
    Optional<Usuario> findByEmail(String email);

    boolean existsByCrm(String crm);
}
