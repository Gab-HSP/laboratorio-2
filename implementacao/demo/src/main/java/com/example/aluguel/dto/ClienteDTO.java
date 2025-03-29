package com.example.aluguel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para Cliente")
public class ClienteDTO {
    
    @Schema(description = "ID do cliente (gerado automaticamente)", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "RG é obrigatório")
    @Schema(example = "1234567", required = true)
    private String rg;
    
    @NotBlank(message = "CPF é obrigatório")
    @Schema(example = "12345678901", required = true)
    private String cpf;
    
    
    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;
    
    @NotBlank(message = "Profissão é obrigatória")
    private String profissao;
    
    @Schema(
        description = "Lista de entidades empregadoras (máximo 3)",
        example = "[\"Empresa A\", \"Empresa B\"]", 
        required = false
    )
    @Size(max = 3, message = "Máximo de 3 entidades empregadoras")
    private List<String> entidadesEmpregadoras = new ArrayList<>();
    
    @Schema(
        description = "Lista de rendimentos auferidos (máximo 3)",
        example = "[2500.00, 3200.50]",  
        required = false
    )
    @Size(max = 3, message = "Máximo de 3 rendimentos auferidos")
    private List<Double> rendimentosAuferidos = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public List<String> getEntidadesEmpregadoras() {
        return entidadesEmpregadoras;
    }

    public void setEntidadesEmpregadoras(List<String> entidadesEmpregadoras) {
        this.entidadesEmpregadoras = entidadesEmpregadoras;
    }

    public List<Double> getRendimentosAuferidos() {
        return rendimentosAuferidos;
    }

    public void setRendimentosAuferidos(List<Double> rendimentosAuferidos) {
        this.rendimentosAuferidos = rendimentosAuferidos;
    }
}