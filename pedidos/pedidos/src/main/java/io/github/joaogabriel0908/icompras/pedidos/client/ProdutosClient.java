package io.github.joaogabriel0908.icompras.pedidos.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.joaogabriel0908.icompras.pedidos.client.representation.ProdutoRepresentation;

@FeignClient(name = "produtos", url = "${icompras.pedidos.clients.produtos.url}")
public interface ProdutosClient {

    @GetMapping("{codigo}")
    ResponseEntity<ProdutoRepresentation> buscarProdutoPorId(@PathVariable("codigo") Long codigo);
}