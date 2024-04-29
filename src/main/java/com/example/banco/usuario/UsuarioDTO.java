package com.example.banco.usuario;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    @NotBlank
    private String nome;
    @Email(message = "email invalido")
    private String email;
    @CPF(message = "CPF Inválido")
    private String cpf;
    private String senha;
    private String telefone;
    private String username;
    private String password;
    private String roles;

}
