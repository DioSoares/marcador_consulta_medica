package com.medConsult.marcadorconsultas.config;

import com.medConsult.marcadorconsultas.model.Perfil;
import com.medConsult.marcadorconsultas.model.Usuario;
import com.medConsult.marcadorconsultas.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private final UsuarioService usuarioService;

    @PostConstruct
    public void cadastrarUsuariosDeTeste() {
        if (usuarioService.usuarioExiste("medico@teste.com")) return;

        Usuario medico = new Usuario();
        medico.setNome("Dr. João");
        medico.setEmail("medico@teste.com");
        medico.setCpf("12345678900");
        medico.setSenha("123456");
        medico.setPerfil(Perfil.MEDICO);
        medico.setTelefone("11987654322");
        medico.setCrm("654321");
        usuarioService.salvarComSenhaCriptografada(medico);

        Usuario paciente = new Usuario();
        paciente.setNome("Maria Paciente");
        paciente.setEmail("paciente@teste.com");
        paciente.setSenha("123456");
        paciente.setCpf("12345678901");
        paciente.setTelefone("11987654321");
        paciente.setPerfil(Perfil.PACIENTE);
        usuarioService.salvarComSenhaCriptografada(paciente);

        System.out.println("Usuários de teste cadastrados com sucesso!");
    }
}
