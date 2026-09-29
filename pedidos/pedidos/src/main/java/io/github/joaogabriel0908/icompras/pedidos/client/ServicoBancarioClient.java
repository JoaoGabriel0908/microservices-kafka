package io.github.joaogabriel0908.icompras.pedidos.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;

@Component 
@Slf4j 
public class ServicoBancarioClient {
    
    public String solicitarPagamento(Pedido pedido) {
        log.info("Solicitando Pagamaneot para o pedido : {}", pedido.getCodigo());
        // Simula a solicitação de pagamento ao serviço bancário
        return UUID.randomUUID().toString();
    }
}
