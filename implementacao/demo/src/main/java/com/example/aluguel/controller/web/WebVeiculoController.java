package com.example.aluguel.controller.web;

import com.example.aluguel.service.AutomovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebVeiculoController {
    
    @Autowired
    private AutomovelService automovelService;
    
    @GetMapping("/veiculos")
    public String listarVeiculos(Model model) {
        model.addAttribute("automoveis", automovelService.listarTodos());
        return "veiculos";
    }
}