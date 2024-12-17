package io.github.alancavalcante_dev.produtospi.controller;

import io.github.alancavalcante_dev.produtospi.model.Produto;
import io.github.alancavalcante_dev.produtospi.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Produto> pegarProdutos() {
        return produtoRepository.findAll();
    }


    @GetMapping("{id}")
    public Produto pegarProdutoPorId(@PathVariable("id") String id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @GetMapping()
    public List<Produto> pegarProdutoPorNome(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }


    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        var id = UUID.randomUUID().toString();
        produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }


    @DeleteMapping("{id}")
    public void removerProduto(@PathVariable("id") String id) {
        produtoRepository.deleteById(id);
    }










}
