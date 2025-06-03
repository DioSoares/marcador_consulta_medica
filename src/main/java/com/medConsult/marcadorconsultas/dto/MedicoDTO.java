package com.medConsult.marcadorconsultas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoDTO {

    private Long id;

    @NotBlank(message = "O nome do médico é obrigatório")
    private String nome;

    @NotBlank(message = "A especialidade é obrigatória")
    private String especialidade;

    @NotBlank(message = "O CRM é obrigatório")
    private String crm;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;
}
