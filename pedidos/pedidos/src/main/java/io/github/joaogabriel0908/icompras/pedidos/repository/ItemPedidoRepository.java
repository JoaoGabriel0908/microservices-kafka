package io.github.joaogabriel0908.icompras.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.joaogabriel0908.icompras.pedidos.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    
}
