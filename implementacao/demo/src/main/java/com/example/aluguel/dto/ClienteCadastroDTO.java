package com.example.aluguel.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteCadastroDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}