package com.example.banco.conta.operacoes;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO {
    @NotNull
    private Long idContaOrigem;
    @NotNull
    private Long idContaDestino;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;
}
