package io.github.joaogabriel0908.icompras.pedidos.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.joaogabriel0908.icompras.pedidos.service.PedidoService;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.AdicaoNovoPagamentoDTO;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.joaogabriel0908.icompras.pedidos.controller.mappers.PedidoMapper;
import io.github.joaogabriel0908.icompras.pedidos.model.ErroResponse;
import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;
import io.github.joaogabriel0908.icompras.pedidos.model.exception.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<Object> criarPedido(@RequestBody NovoPedidoDTO novoPedidoDTO) {
        try {
            var pedido = pedidoMapper.map(novoPedidoDTO);
            Pedido pedidoCriado = pedidoService.criarPedido(pedido);
            return ResponseEntity.ok(pedidoCriado);
        } catch (ValidationException e) {
            String campo = e.getField();
            String erro = e.getMessage();
            ErroResponse erroResponse = new ErroResponse(campo, erro);
            return ResponseEntity.badRequest().body(erroResponse);
        }

    }

    @PostMapping("pagamentos")
    public ResponseEntity<Object> adicionarNovoPagamento(
            @RequestBody AdicaoNovoPagamentoDTO novoPagamentoDTO) {
        try {
            pedidoService.adicionarNovoPagamento(
                    novoPagamentoDTO.codigoPedido(),
                    novoPagamentoDTO.dadosCartao(),
                    novoPagamentoDTO.tipoPagamento());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            var erro = new ErroResponse("Item não encontrado", "codigoPedido" + e.getMessage());
            return ResponseEntity.status(500).body(erro);
        }

    }

}
