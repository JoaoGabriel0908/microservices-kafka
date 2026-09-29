package io.github.joaogabriel0908.icompras.pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.joaogabriel0908.icompras.pedidos.client.representation.ClienteRepresentation;

@FeignClient(name = "clientes", url = "${icompras.pedidos.clients.clientes.url}")
public interface ClientesClient {

    @GetMapping("/{id}")
    ResponseEntity<ClienteRepresentation> getClienteByCodigo(@PathVariable("id") Long codigo);
}
