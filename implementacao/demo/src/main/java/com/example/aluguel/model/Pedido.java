package com.example.aluguel.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Automovel Automovel;
    
    private LocalDateTime dataPedido;
    private String status; // "PENDENTE", "APROVADO", "NEGADO"
    

    public Cliente getCliente() {
        return cliente;
    }
    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public Long getId() {
        return id;
    }
    public String getStatus() {
        return status;
    }
    public Automovel getAutomovel() {
        return Automovel;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setAutomovel(Automovel Automovel) {
        this.Automovel = Automovel;
    }
}