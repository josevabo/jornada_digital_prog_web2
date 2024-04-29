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
public class SaqueDTO {
    @NotNull
    private Long idConta;
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal valor;
}
