package com.aula.baozi.store.controller;

import com.aula.baozi.store.model.Cliente;
import com.aula.baozi.store.repository.ClienteRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Cadastrar cliente
    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Listar todos os clientes
    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Excluir cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}