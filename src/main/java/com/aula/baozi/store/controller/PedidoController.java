package com.aula.baozi.store.controller;

import com.aula.baozi.store.model.Pedido;
import com.aula.baozi.store.repository.PedidoRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    // Cadastrar pedido
    @PostMapping
    public Pedido cadastrar(@RequestBody Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Listar todos os pedidos
    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}