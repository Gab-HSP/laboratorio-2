package com.example.aluguel.controller.web;

import com.example.aluguel.model.Automovel;
import com.example.aluguel.model.Cliente;
import com.example.aluguel.service.PedidoService;
import com.example.aluguel.service.AutomovelService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class WebPedidoController {
    
    private final PedidoService pedidoService;
    private final AutomovelService automovelService;

    public WebPedidoController(PedidoService pedidoService, AutomovelService automovelService) {
        this.pedidoService = pedidoService;
        this.automovelService = automovelService;
    }
    
    
    @GetMapping
    public String listarPedidos(@AuthenticationPrincipal Cliente cliente, Model model) {
        model.addAttribute("pedidos", pedidoService.listarPedidosPorCliente(cliente));
        return "pedidos";
    }
    
    @PostMapping("/novo/{veiculoId}")  
    public String criarPedido(
            @PathVariable Long veiculoId,
            @AuthenticationPrincipal Cliente cliente) {
        
        Automovel automovel = automovelService.buscarPorId(veiculoId);
        pedidoService.criarPedido(automovel, cliente);
        return "redirect:/pedidos";  
    }
}