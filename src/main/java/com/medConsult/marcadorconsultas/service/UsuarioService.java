package com.medConsult.marcadorconsultas.service;

import com.medConsult.marcadorconsultas.dto.UsuarioDTO;
import com.medConsult.marcadorconsultas.exception.ConflictException;
import com.medConsult.marcadorconsultas.model.Usuario;
import com.medConsult.marcadorconsultas.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getPerfil().name())
                .build();
    }

    public void salvarComSenhaCriptografada (Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioRepository.save(usuario);
    }

    public Usuario salvar(UsuarioDTO dto) {
        if (usuarioRepository.existsByCpf(dto.getCpf())) {
            throw new ConflictException("CPF já cadastrado");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new ConflictException("Email já cadastrado");
        }
        if (dto.getPerfil().name().equals("MEDICO") && usuarioRepository.existsByCrm(dto.getCrm())) {
            throw new ConflictException("CRM já cadastrado");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .telefone(dto.getTelefone())
                .perfil(dto.getPerfil())
                .especialidade(dto.getEspecialidade())
                .crm(dto.getCrm())
                .build();

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findByid(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    public boolean usuarioExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

}
