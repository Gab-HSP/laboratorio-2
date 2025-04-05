package com.example.aluguel.service;

import com.example.aluguel.model.Automovel;
import com.example.aluguel.model.Cliente;
import com.example.aluguel.model.Pedido;
import com.example.aluguel.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public Pedido criarPedido(Automovel automovel, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setAutomovel(automovel);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        return pedidoRepository.save(pedido);
    }
    
    public List<Pedido> listarPedidosPorCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }
}