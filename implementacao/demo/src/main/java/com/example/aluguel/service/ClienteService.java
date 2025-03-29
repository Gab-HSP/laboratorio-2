package com.example.aluguel.service;

import com.example.aluguel.dto.ClienteDTO;
import com.example.aluguel.model.Cliente;
import com.example.aluguel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        validarCliente(clienteDTO);
        
        Cliente cliente = new Cliente();
        cliente.setRg(clienteDTO.getRg());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        
        // Adiciona entidades empregadoras
        if (clienteDTO.getEntidadesEmpregadoras() != null) {
            for (String entidade : clienteDTO.getEntidadesEmpregadoras()) {
                cliente.adicionarEntidadeEmpregadora(entidade);
            }
        }
        
        // Adiciona rendimentos
        if (clienteDTO.getRendimentosAuferidos() != null) {
            for (Double rendimento : clienteDTO.getRendimentosAuferidos()) {
                cliente.adicionarRendimento(rendimento);
            }
        }
        
        Cliente savedCliente = clienteRepository.save(cliente);
        return convertToDTO(savedCliente);
    }

    public List<ClienteDTO> getAllClientes() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO getClienteById(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return convertToDTO(cliente);
    }

    @Transactional
    public ClienteDTO updateCliente(Long id, ClienteDTO clienteDTO) {
        validarCliente(clienteDTO);
        
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        cliente.setRg(clienteDTO.getRg());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        
        // Atualiza entidades empregadoras
        cliente.getEntidadesEmpregadoras().clear();
        if (clienteDTO.getEntidadesEmpregadoras() != null) {
            for (String entidade : clienteDTO.getEntidadesEmpregadoras()) {
                cliente.adicionarEntidadeEmpregadora(entidade);
            }
        }
        
        // Atualiza rendimentos
        cliente.getRendimentosAuferidos().clear();
        if (clienteDTO.getRendimentosAuferidos() != null) {
            for (Double rendimento : clienteDTO.getRendimentosAuferidos()) {
                cliente.adicionarRendimento(rendimento);
            }
        }
        
        Cliente updatedCliente = clienteRepository.save(cliente);
        return convertToDTO(updatedCliente);
    }

    public void deleteCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setRg(cliente.getRg());
        dto.setCpf(cliente.getCpf());
        dto.setEndereco(cliente.getEndereco());
        dto.setProfissao(cliente.getProfissao());
        dto.setEntidadesEmpregadoras(cliente.getEntidadesEmpregadoras());
        dto.setRendimentosAuferidos(cliente.getRendimentosAuferidos());
        return dto;
    }
    
    private void validarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() != null) {
            throw new IllegalArgumentException("ID não deve ser fornecido para novo cliente");
        }

        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        
        if (clienteDTO.getEntidadesEmpregadoras() != null && 
            clienteDTO.getEntidadesEmpregadoras().size() > 3) {
            throw new RuntimeException("Máximo de 3 entidades empregadoras permitidas");
        }
        
        if (clienteDTO.getRendimentosAuferidos() != null && 
            clienteDTO.getRendimentosAuferidos().size() > 3) {
            throw new RuntimeException("Máximo de 3 rendimentos auferidos permitidos");
        }
    }
}