package com.example.banco.cliente;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    private ClienteDTO convertDto(Cliente usuario) {
        return this.modelMapper.map(usuario, ClienteDTO.class);
    }

    private Cliente convertFromDto(ClienteDTO clienteDto) {
        return this.modelMapper.map(clienteDto, Cliente.class);
    }

    public List<ClienteDTO> listarClientes() {
        return this.clienteRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public ClienteDTO salvar(ClienteDTO clienteDto) {
        var usuario = this.convertFromDto(clienteDto);
        usuario.setStatus(Status.ATIVO);
        var savedUsuario = this.clienteRepository.save(usuario);
        return this.convertDto(savedUsuario);
    }

    public Optional<ClienteDTO> buscarPorCpf(String cpf) {
        return this.clienteRepository.findByCpf(cpf).map(this::convertDto);
    }

    public void excluir(String cpf) {
        var usuario = this.clienteRepository.findByCpf(cpf).orElseThrow();
        System.out.println(usuario);
        this.clienteRepository.delete(usuario);
    }

    public ClienteDTO atualizar(ClienteDTO clienteDto) {
        var usuario = this.clienteRepository.findByCpf(clienteDto.getCpf()).orElseThrow();
        usuario.setNome(clienteDto.getNome());
        return this.convertDto(clienteRepository.save(usuario));
    }

}
