package com.example.banco.conta;

import com.example.banco.login.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService service;
    private final JwtService jwtService;

    @GetMapping("{cpf}")
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public List<ContaResponse> listar(@PathVariable("cpf") String cpf, @RequestHeader (name="Authorization") String bearerToken) {
//        String token = bearerToken.substring(7);
//        Usuario u = (Usuario) jwtService.getUserDetails(token);
//        if(!u.getUsername().equals(cpf)){
//          throw new AccessDeniedException("Você pode apenas acessar apenas suas próprias contas");
//        }

        return service.findByCliente(cpf);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public ContaDTO salvar(@RequestBody @Valid ContaDTO emprestimo) {
        return service.salvar(emprestimo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole(T(com.example.banco.usuario.Role).ADMIN.name(),T(com.example.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity excluir (@PathVariable("id") Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
