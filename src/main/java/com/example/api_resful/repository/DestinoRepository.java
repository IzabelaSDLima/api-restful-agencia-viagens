package com.example.api_resful.repository;

import com.example.api_resful.entity.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNomeContainingOrLocalizacaoContaining(String nome, String localizacao);
}
