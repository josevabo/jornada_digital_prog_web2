package com.example.banco.conta;

import com.example.banco.cliente.ClienteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ContaResponse implements Serializable {
    private Long id;
    private ClienteDTO cliente;
    private BigDecimal saldo;
}
