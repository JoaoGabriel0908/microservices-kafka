package io.github.joaogabriel0908.icompras.pedidos.controller.dto;

import io.github.joaogabriel0908.icompras.pedidos.model.enums.TipoPagamento;

public record DadosPagamentoDTO(
    String dados,
    TipoPagamento tipoPagamento
) {
}
