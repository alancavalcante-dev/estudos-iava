package io.github.alancavalcante_dev.produtospi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import io.github.alancavalcante_dev.produtospi.model.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findByNome(String nome);
}
