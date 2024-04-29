package com.example.banco.conta.operacoes;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoRetornoDTO {
    private Long idConta;
    private BigDecimal saldo;
}
