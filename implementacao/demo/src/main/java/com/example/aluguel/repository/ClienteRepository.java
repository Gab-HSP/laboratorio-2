package com.example.aluguel.repository;

import com.example.aluguel.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    Optional<Cliente> findByEmail(String email);
}