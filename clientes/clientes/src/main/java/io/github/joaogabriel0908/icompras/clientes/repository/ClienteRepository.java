package io.github.joaogabriel0908.icompras.clientes.repository;

import io.github.joaogabriel0908.icompras.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
