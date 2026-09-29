package io.github.joaogabriel0908.icompras.pedidos.controller.mappers;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import io.github.joaogabriel0908.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.joaogabriel0908.icompras.pedidos.model.ItemPedido;
import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;
import io.github.joaogabriel0908.icompras.pedidos.model.enums.StatusPedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    
    ItemPedidoMapper itemPedidoMapper = Mappers.getMapper(ItemPedidoMapper.class);

    @Mapping (source = "itens", target = "itens", qualifiedByName = "mapItens")
    @Mapping (source = "dadosPagamento", target = "dadosPagamento")
    Pedido map(NovoPedidoDTO novoPedidoDTO);

    @Named ("mapItens")
    default List<ItemPedido> mapItemPedidos(List<ItemPedidoDTO> itemPedidoDTOs) {
        return itemPedidoDTOs.stream().map(itemPedidoMapper::map).toList();
    }

    @AfterMapping 
    default void afterMapping(@MappingTarget Pedido pedido) {
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setDataPedido(LocalDateTime.now());

        BigDecimal total = calcularTotal(pedido.getItens());
        pedido.setTotal(total);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
    }

    private static BigDecimal calcularTotal(List<ItemPedido> itens) {
        return itens.stream()
                    .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add)
                    .abs();
    }
}
