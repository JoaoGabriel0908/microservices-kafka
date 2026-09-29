package io.github.joaogabriel0908.icompras.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.joaogabriel0908.icompras.pedidos.controller.dto.RecebimentoCallbackPagamentoDTO;
import io.github.joaogabriel0908.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos/callback-pagamentos")
public class RecebimentoCallbackPagamentoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Object> atualizarStatusPagamento(
            @RequestBody RecebimentoCallbackPagamentoDTO recebimentoCallbackPagamentoDTO,
            @RequestHeader(required = true, name = "apiKey") String apiKey) {

        if (apiKey == null || apiKey.isEmpty()) {
            return ResponseEntity.status(401).build();
        } else {
            pedidoService.atualizarStatusPagamento(
                    recebimentoCallbackPagamentoDTO.codigo(),
                    recebimentoCallbackPagamentoDTO.chavePagamento(),
                    recebimentoCallbackPagamentoDTO.status(),
                    recebimentoCallbackPagamentoDTO.observacoes());
        }

        return ResponseEntity.ok().build();
    }
}
