package io.github.joaogabriel0908.icompras.produtos.controller;
import io.github.joaogabriel0908.icompras.produtos.service.ProdutoService;
import io.github.joaogabriel0908.icompras.produtos.model.Produto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/produtos")
@RequiredArgsConstructor 
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> salvarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(produtoSalvo);
    }
    
    @GetMapping("/{codigo}") 
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable("codigo") Long codigo) {
        Produto produto = produtoService.buscarProdutoPorId(codigo);
        return produto != null ? ResponseEntity.ok(produto) : ResponseEntity.notFound().build();
    }
}
