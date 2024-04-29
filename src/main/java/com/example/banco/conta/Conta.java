package com.example.banco.conta;

import com.example.banco.cliente.Cliente;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "CONTA")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    private LocalDate dataAbertura;
    private BigDecimal saldo;

}
