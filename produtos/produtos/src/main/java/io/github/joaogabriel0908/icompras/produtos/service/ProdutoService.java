package io.github.joaogabriel0908.icompras.produtos.service;
import org.springframework.stereotype.Service;

import io.github.joaogabriel0908.icompras.produtos.model.Produto;
import io.github.joaogabriel0908.icompras.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarProdutoPorId(Long codigo) {
        return produtoRepository.findById(codigo).orElse(null);
    }

    public void deletarProduto(Long codigo) {
        produtoRepository.deleteById(codigo);
    }
}
