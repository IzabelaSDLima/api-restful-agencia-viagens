package com.example.api_resful.dao;

import com.example.api_resful.entity.Destino;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class DestinoDAO {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public DestinoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("exemplo");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Destino saveDestino(Destino destino) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(destino);
            entityManager.getTransaction().commit();
            return destino;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar destino", e);
        }
    }

    public List<Destino> getAllDestinos() {
        return entityManager.createQuery("SELECT d FROM Destino d", Destino.class).getResultList();
    }

    public Destino getDestinoById(Long id) {
        return entityManager.find(Destino.class, id);
    }

    public Destino updateDestino(Destino destino) {
        try {
            entityManager.getTransaction().begin();
            Destino updatedDestino = entityManager.merge(destino);
            entityManager.getTransaction().commit();
            return updatedDestino;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar destino", e);
        }
    }

    public void deleteDestino(Long id) {
        try {
            entityManager.getTransaction().begin();
            Destino destino = getDestinoById(id);
            if (destino != null) {
                entityManager.remove(destino);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir destino", e);
        }
    }
}
