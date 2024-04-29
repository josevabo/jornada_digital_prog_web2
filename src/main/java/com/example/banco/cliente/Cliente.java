package com.example.banco.cliente;

import com.example.banco.util.Pessoa;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "CLIENTE")
public class Cliente extends Pessoa {

    @Column(unique = true)
    private String cpf;
    private Status status;
    private LocalDate dataNascimento;
}
