package io.github.joaogabriel0908.icompras.pedidos.controller.dto;
import java.util.List;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.DadosPagamentoDTO;

public record NovoPedidoDTO(
    Long codigoCliente,
    DadosPagamentoDTO dadosPagamento,
    List<ItemPedidoDTO> itens
) {
}
