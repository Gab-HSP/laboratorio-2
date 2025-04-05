package com.example.aluguel.service;

import com.example.aluguel.dto.ClienteCadastroDTO;
import com.example.aluguel.dto.ClienteDTO;
import com.example.aluguel.model.Cliente;
import com.example.aluguel.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para cadastro simplificado
    @Transactional
    public Cliente cadastrarCliente(ClienteCadastroDTO cadastroDTO) {
        if (clienteRepository.existsByEmail(cadastroDTO.getEmail())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        
        Cliente cliente = new Cliente();
        cliente.setNome(cadastroDTO.getNome());
        cliente.setEmail(cadastroDTO.getEmail());
        cliente.setSenha(passwordEncoder.encode(cadastroDTO.getSenha()));
        
        return clienteRepository.save(cliente);
    }

    @Transactional
    public ClienteDTO createCliente(ClienteDTO clienteDTO) {
        validarCliente(clienteDTO);
        
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));
        cliente.setRg(clienteDTO.getRg());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        
        if (clienteDTO.getEntidadesReguladoras() != null) {
            clienteDTO.getEntidadesReguladoras().forEach(cliente::adicionarEntidadeReguladora);
        }
        
        if (clienteDTO.getRendimentosAufericos() != null) {
            clienteDTO.getRendimentosAufericos().forEach(cliente::adicionarRendimento);
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
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        cliente.setNome(clienteDTO.getNome());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setRg(clienteDTO.getRg());
        cliente.setEndereco(clienteDTO.getEndereco());
        cliente.setProfissao(clienteDTO.getProfissao());
        
        cliente.getEntidadesReguladoras().clear();
        if (clienteDTO.getEntidadesReguladoras() != null) {
            clienteDTO.getEntidadesReguladoras().forEach(cliente::adicionarEntidadeReguladora);
        }
        
        cliente.getRendimentosAufericos().clear();
        if (clienteDTO.getRendimentosAufericos() != null) {
            clienteDTO.getRendimentosAufericos().forEach(cliente::adicionarRendimento);
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
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        dto.setRg(cliente.getRg());
        dto.setCpf(cliente.getCpf());
        dto.setEndereco(cliente.getEndereco());
        dto.setProfissao(cliente.getProfissao());
        dto.setEntidadesReguladoras(cliente.getEntidadesReguladoras());
        dto.setRendimentosAufericos(cliente.getRendimentosAufericos());
        return dto;
    }
    
    private void validarCliente(ClienteDTO clienteDTO) {
        if (clienteDTO.getId() != null) {
            throw new IllegalArgumentException("ID não deve ser fornecido para novo cliente");
        }

        if (clienteRepository.existsByCpf(clienteDTO.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }
        
        if (clienteDTO.getEntidadesReguladoras() != null && 
            clienteDTO.getEntidadesReguladoras().size() > 3) {
            throw new RuntimeException("Máximo de 3 entidades reguladoras permitidas");
        }
        
        if (clienteDTO.getRendimentosAufericos() != null && 
            clienteDTO.getRendimentosAufericos().size() > 3) {
            throw new RuntimeException("Máximo de 3 rendimentos aufericos permitidos");
        }
    }
}