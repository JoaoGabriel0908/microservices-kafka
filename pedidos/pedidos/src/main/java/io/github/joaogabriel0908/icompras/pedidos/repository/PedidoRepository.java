package io.github.joaogabriel0908.icompras.pedidos.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    Optional<Pedido> findByCodigoAndChavePagamento(Long codigo, String chavePagamento); 
}
