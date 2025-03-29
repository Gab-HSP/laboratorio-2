package com.example.aluguel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    
    private static final int LIMITE_RENDIMENTO_ENTIDADE = 3;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;
    
    @ElementCollection
    private List<String> entidadesEmpregadoras = new ArrayList<>();
    
    @ElementCollection
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

    public void adicionarEntidadeEmpregadora(String entidade) {
        if (entidadesEmpregadoras.size() >= LIMITE_RENDIMENTO_ENTIDADE) {
            throw new IllegalStateException("Limite máximo de entidades empregadoras atingido (3)");
        }
        entidadesEmpregadoras.add(entidade);
    }
    
    public void adicionarRendimento(Double rendimento) {
        if (rendimentosAuferidos.size() >= LIMITE_RENDIMENTO_ENTIDADE) {
            throw new IllegalStateException("Limite máximo de rendimentos auferidos atingido (3)");
        }
        rendimentosAuferidos.add(rendimento);
    }
    
    public void removerEntidadeEmpregadora(String entidade) {
        entidadesEmpregadoras.remove(entidade);
    }
    
    public void removerRendimento(Double rendimento) {
        rendimentosAuferidos.remove(rendimento);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco='" + endereco + '\'' +
                ", profissao='" + profissao + '\'' +
                '}';
    }
}