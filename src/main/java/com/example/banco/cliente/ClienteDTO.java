package com.example.banco.cliente;

import com.example.banco.cliente.validacao.IdadeMinima;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDTO {

    @NotBlank
    private String nome;
    @Email(message = "email invalido")
    private String email;
    @NotNull
    @CPF(message = "CPF Inválido")
    private String cpf;
    @NotNull
    @IdadeMinima(idade = 18, message = "Cliente deve ser maior de idade")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
}
