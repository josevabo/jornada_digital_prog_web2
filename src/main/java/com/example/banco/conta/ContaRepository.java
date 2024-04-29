package com.example.banco.conta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    List<Conta> findByCliente_Cpf(String cpf);
}
