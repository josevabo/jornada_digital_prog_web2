package com.example.banco.conta.operacoes;

import com.example.banco.cliente.Cliente;
import com.example.banco.cliente.ClienteDTO;
import com.example.banco.cliente.ClienteRepository;
import com.example.banco.conta.Conta;
import com.example.banco.conta.ContaRepository;
import com.example.banco.exception.NaoEncontradoException;
import com.example.banco.exception.NegocioException;
import com.example.banco.login.JwtService;
import com.example.banco.usuario.Usuario;
import com.example.banco.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperacoesContaService {
    private final ContaRepository contaRepository;
    private final JwtService jwtService;


    public SaldoRetornoDTO saldo(Long idConta, String bearerToken) {
        Conta conta = contaRepository.findById(idConta).orElseThrow(() -> new NaoEncontradoException("Conta não localizada"));

        validateUserRelatedToAccount(bearerToken, conta);

        return SaldoRetornoDTO.builder().saldo(conta.getSaldo()).idConta(conta.getId()).build();
    }

    public void depositar(DepositoDTO dados, String bearerToken) {
        Conta conta = contaRepository.findById(dados.getIdConta()).orElseThrow(() -> new NaoEncontradoException("Conta não localizada"));

        validateUserRelatedToAccount(bearerToken, conta);

        conta.setSaldo(conta.getSaldo().add(dados.getValor()));
    }

    public void sacar(SaqueDTO dados, String bearerToken) {
        Conta conta = contaRepository.findById(dados.getIdConta()).orElseThrow(() -> new NaoEncontradoException("Conta não localizada"));

        validateUserRelatedToAccount(bearerToken, conta);
        var valorSaque = dados.getValor();
        if(valorSaque.compareTo(conta.getSaldo()) > 0)
            throw new NegocioException("Saldo insuficiente: R$ "+ conta.getSaldo());

        conta.setSaldo(conta.getSaldo().subtract(valorSaque));
        contaRepository.save(conta);
    }

    public void transferir(TransferenciaDTO dados, String bearerToken) {
        Conta contaOrigem = contaRepository.findById(dados.getIdContaOrigem()).orElseThrow(() -> new NaoEncontradoException("Conta origem não localizada"));
        Conta contaDestino = contaRepository.findById(dados.getIdContaDestino()).orElseThrow(() -> new NaoEncontradoException("Conta destino não localizada"));

        validateUserRelatedToAccount(bearerToken, contaOrigem);

        var valorSaque = dados.getValor();
        if(valorSaque.compareTo(contaOrigem.getSaldo()) > 0)
            throw new NegocioException("Saldo insuficiente: R$ "+ contaOrigem.getSaldo());

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorSaque));
        contaDestino.setSaldo(contaDestino.getSaldo().add(valorSaque));
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }

    private void validateUserRelatedToAccount(String bearerToken, Conta conta){
        String token = bearerToken.substring(7);
        Usuario u = (Usuario) jwtService.getUserDetails(token);
        String cpf = conta.getCliente().getCpf();

        if(!u.getUsername().equals(cpf)){
            throw new AccessDeniedException("Você pode acessar apenas suas próprias contas");
        }
    }

}
