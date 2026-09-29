package io.github.joaogabriel0908.icompras.pedidos.validator;

import org.springframework.http.ResponseEntity;
import io.github.joaogabriel0908.icompras.pedidos.client.representation.ProdutoRepresentation;
import io.github.joaogabriel0908.icompras.pedidos.client.representation.ClienteRepresentation;
import org.springframework.stereotype.Component;

import feign.FeignException;
import io.github.joaogabriel0908.icompras.pedidos.client.ClientesClient;
import io.github.joaogabriel0908.icompras.pedidos.client.ProdutosClient;
import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;
import io.github.joaogabriel0908.icompras.pedidos.model.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j 
public class PedidoValidator {

    private final ProdutosClient produtosClient;
    private final ClientesClient clientesClient;

    public void validarPedido(Pedido pedido) {
        Long codigoCliente = pedido.getCodigoCliente();
        validarCliente(codigoCliente);
        pedido.getItens().forEach(item -> validarItemProduto(item.getCodigoProduto()));
    }

    private void validarCliente(Long codigoCliente) {
        try {
            var response = clientesClient.getClienteByCodigo(codigoCliente);
            ClienteRepresentation cliente = response.getBody();
            log.info("Cliente encontrado: {}", cliente);
        } catch (FeignException e) {
            throw new ValidationException("codigoCliente", "Cliente de código " + codigoCliente + " não encontrado");
        }

    }

    private void validarItemProduto(Long codigoProduto) {
        try {
            var response = produtosClient.buscarProdutoPorId(codigoProduto);
            ProdutoRepresentation produto = response.getBody();
            log.info("Produto encontrado: {}", produto);
        } catch (FeignException e) {
            throw new ValidationException("codigoProduto", "Produto de código " + codigoProduto + " não encontrado");
        }
    }

}
