package io.github.joaogabriel0908.icompras.clientes.service;
import io.github.joaogabriel0908.icompras.clientes.model.Cliente;
import io.github.joaogabriel0908.icompras.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor 
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente obterClientePorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }
    
}
