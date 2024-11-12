package com.example.api_resful.service;


import com.example.api_resful.entity.Destino;
import com.example.api_resful.repository.DestinoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public Destino cadastrarDestino(Destino destino) {
        return destinoRepository.save(destino);
    }

    public List<Destino> listarDestinos() {
        return destinoRepository.findAll();
    }

    public List<Destino> pesquisarDestinos(String termo) {
        return destinoRepository.findByNomeContainingOrLocalizacaoContaining(termo, termo);
    }

    public Optional<Destino> visualizarDetalhes(Long id) {
        return destinoRepository.findById(id);
    }

    public Destino avaliarDestino(Long id, int nota) {
        Destino destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));
        destino.setMediaAvaliacao((destino.getMediaAvaliacao() * destino.getTotalAvaliacoes() + nota) / (destino.getTotalAvaliacoes() + 1));
        destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);
        return destinoRepository.save(destino);
    }

    public void excluirDestino(Long id) {
        destinoRepository.deleteById(id);
    }
}
