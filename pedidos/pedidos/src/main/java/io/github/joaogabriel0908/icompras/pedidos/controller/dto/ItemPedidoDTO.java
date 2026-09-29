package io.github.joaogabriel0908.icompras.pedidos.controller.dto;

import java.math.BigDecimal;

public record ItemPedidoDTO(
    Long codigoProduto,
    Integer quantidade,
    BigDecimal valorUnitario
) {
}
