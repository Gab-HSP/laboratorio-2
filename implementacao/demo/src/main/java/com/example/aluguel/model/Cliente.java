package com.example.aluguel.model;

import jakarta.persistence.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Entity
public class Cliente implements UserDetails {
    private static final int LIMITE_REGULADORAS_AUFERICOS = 3;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
    private String senha;
    
    @OneToOne
    private Automovel automovelAlugado;
    
    @OneToOne
    private Automovel automovelProprietario;
    
    private String rg;
    private String cpf;
    private String endereco;
    private String profissao;
    
    @ElementCollection
    private List<String> entidadesReguladoras = new ArrayList<>();
    
    @ElementCollection
    private List<Double> rendimentosAufericos = new ArrayList<>();
    
    // UserDetails methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRg() {
        return rg;
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

    public void setEntidadesReguladoras(List<String> entidadesReguladoras) {
        this.entidadesReguladoras = entidadesReguladoras;
    }

    public List<String> getEntidadesReguladoras() {
        return entidadesReguladoras;
    }

    public void setRendimentosAufericos(List<Double> rendimentosAufericos) {
        this.rendimentosAufericos = rendimentosAufericos;
    }

    public List<Double> getRendimentosAufericos() {
        return rendimentosAufericos;
    }

    public void adicionarEntidadeReguladora(String entidade) {
        if (entidadesReguladoras.size() >= LIMITE_REGULADORAS_AUFERICOS) {
            throw new IllegalStateException("Limite máximo de entidades reguladoras atingido");
        }
        entidadesReguladoras.add(entidade);
    }
    
    public void adicionarRendimento(Double rendimento) {
        if (rendimentosAufericos.size() >= LIMITE_REGULADORAS_AUFERICOS) {
            throw new IllegalStateException("Limite máximo de rendimentos aufericos atingido");
        }
        rendimentosAufericos.add(rendimento);
    }
}