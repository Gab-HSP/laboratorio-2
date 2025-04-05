package com.example.aluguel.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "DTO para Cliente")
public class ClienteDTO {
    @Schema(description = "ID do cliente (gerado automaticamente)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Email é obrigatório")
    private String email;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;
    
    @Schema(description = "Lista de entidades reguladoras (máximo 3)")
    private List<String> entidadesReguladoras;
    
    @Schema(description = "Lista de rendimentos auferidos (máximo 3)")
    private List<Double> rendimentosAufericos;
}