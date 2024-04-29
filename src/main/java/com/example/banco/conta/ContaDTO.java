package com.example.banco.conta;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {
    private Long id;
    private String cpfCliente;
    private BigDecimal saldo;
}
