package com.aula.baozi.store.controller;

import com.aula.baozi.store.model.Produto;
import com.aula.baozi.store.repository.ProdutoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Cadastrar produto
    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    // Buscar produto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}