package io.github.joaogabriel0908.icompras.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.github.joaogabriel0908.icompras.produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}