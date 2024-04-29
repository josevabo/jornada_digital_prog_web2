package com.example.banco.conta;

import com.example.banco.cliente.Cliente;
import com.example.banco.cliente.ClienteDTO;
import com.example.banco.cliente.ClienteRepository;
import com.example.banco.exception.NaoEncontradoException;
import com.example.banco.exception.NegocioException;
import com.example.banco.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaService {
    private final ContaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;


    private ContaDTO convertDto(Conta autor) {
        return this.modelMapper.map(autor, ContaDTO.class);
    }
    private ClienteDTO convertDto(Cliente cliente) {
        return this.modelMapper.map(cliente, ClienteDTO.class);
    }


    public List<ContaResponse> findByCliente(String cpf) {
     return repository.findByCliente_Cpf(cpf).stream()
              .map(e -> ContaResponse.builder()
                      .cliente(convertDto(e.getCliente()))
                      .saldo(e.getSaldo())
                      .build())
              .collect(Collectors.toList());
    }
    
    public ContaDTO salvar(ContaDTO dto) {
        var cliente = clienteRepository.findById(dto.getId()).orElseThrow(() -> new NaoEncontradoException("O cliente informado não existe"));
        if(dto.getSaldo().compareTo(BigDecimal.ZERO) <= 0)
            throw new NegocioException("Conta deve ser aberta com saldo maior que zero");
        var usuario = usuarioRepository.findByCpf(dto.getCpfCliente());
        var saved = this.repository.save(Conta.builder().
                cliente(cliente)
                .dataAbertura(LocalDate.now())
                .build());
        return ContaDTO.builder().cpfCliente(saved.getCliente().getCpf()).saldo(saved.getSaldo()).id(saved.getId()).build();
    }

    public void excluir(Long id) {
        Conta conta = repository.findById(id).orElseThrow(() -> new NaoEncontradoException("Id de conta não localizado"));

        repository.delete(conta);
    }
}
