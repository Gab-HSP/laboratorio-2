package com.example.aluguel.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.aluguel.dto.ClienteCadastroDTO;
import com.example.aluguel.service.ClienteService;

@Controller
@RequestMapping("/registro")
public class WebClienteController {

    private final ClienteService clienteService;

    public WebClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("cliente", new ClienteCadastroDTO());
        return "registro";
    }

    @PostMapping
    public String processRegistration(
        @ModelAttribute("cliente") ClienteCadastroDTO clienteDTO,
        RedirectAttributes redirectAttributes) {
        
        clienteService.cadastrarCliente(clienteDTO);
        redirectAttributes.addFlashAttribute("success", "Registro realizado com sucesso! Faça login.");
        return "redirect:/login";  // Remova as aspas extras
    }
}