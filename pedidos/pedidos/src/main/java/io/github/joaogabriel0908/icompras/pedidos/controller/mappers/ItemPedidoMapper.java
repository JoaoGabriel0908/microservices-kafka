package io.github.joaogabriel0908.icompras.pedidos.controller.mappers;

import org.mapstruct.Mapper;

import io.github.joaogabriel0908.icompras.pedidos.model.ItemPedido;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.ItemPedidoDTO;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {
    
    ItemPedido map(ItemPedidoDTO itemPedidoDTO);
}
