package com.medConsult.marcadorconsultas.dto;

import com.medConsult.marcadorconsultas.model.Perfil;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    private Perfil perfil;

    private String especialidade;

    private String crm;

    public record LoginRequest(
        @NotBlank(message = "O email é obrigatório") String email,
        @NotBlank(message = "A senha é obrigatória") String senha
    ) {}

    public record LoginResponse(
        String token
    ) {}
}
