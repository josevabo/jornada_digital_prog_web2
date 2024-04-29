package com.example.banco.conta.operacoes;

import com.example.banco.login.JwtService;
import com.example.banco.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas/operacoes")
@RequiredArgsConstructor
public class OperacoesContaController {

    private final OperacoesContaService service;

    @PutMapping("/sacar")
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public void sacar(@Valid @RequestBody SaqueDTO dados, @RequestHeader (name="Authorization") String bearerToken) {

        service.sacar(dados, bearerToken);
    }

    @GetMapping("/{idConta}/saldo")
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public SaldoRetornoDTO saldo(@PathVariable Long idConta, @RequestHeader (name="Authorization") String bearerToken) {
        return service.saldo(idConta, bearerToken);
    }

    @DeleteMapping("/depositar")
    @PreAuthorize("hasRole(T(com.example.banco.usuario.Role).CLIENTE.name())")
    public ResponseEntity depositar(@Valid @RequestBody DepositoDTO dados, @RequestHeader (name="Authorization") String bearerToken) {
        service.depositar(dados, bearerToken);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/transferir")
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity transferir(@Valid @RequestBody TransferenciaDTO dados, @RequestHeader (name="Authorization") String bearerToken) {
        service.transferir(dados, bearerToken);
        return ResponseEntity.noContent().build();
    }
}
