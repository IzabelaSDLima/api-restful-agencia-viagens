package com.example.api_resful.controller;


import com.example.api_resful.entity.Destino;
import com.example.api_resful.service.DestinoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinos")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    @PostMapping
    public ResponseEntity<Destino> cadastrarDestino(@RequestBody Destino destino) {
        Destino novoDestino = destinoService.cadastrarDestino(destino);
        return ResponseEntity.ok(novoDestino);
    }

    @GetMapping
    public ResponseEntity<List<Destino>> listarDestinos() {
        return ResponseEntity.ok(destinoService.listarDestinos());
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<Destino>> pesquisarDestinos(@RequestParam String termo) {
        return ResponseEntity.ok(destinoService.pesquisarDestinos(termo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destino> visualizarDetalhes(@PathVariable Long id) {
        return destinoService.visualizarDetalhes(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/avaliacao")
    public ResponseEntity<Destino> avaliarDestino(@PathVariable Long id, @RequestParam int nota) {
        if (nota < 1 || nota > 10) {
            return ResponseEntity.badRequest().body(null);
        }
        Destino destinoAvaliado = destinoService.avaliarDestino(id, nota);
        return ResponseEntity.ok(destinoAvaliado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDestino(@PathVariable Long id) {
        destinoService.excluirDestino(id);
        return ResponseEntity.noContent().build();
    }
}

