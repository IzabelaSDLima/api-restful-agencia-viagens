package com.example.api_resful.dao;

import com.example.api_resful.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class UserDAO {

    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;


    public UserDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("exemplo");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User saveUser(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar usuário", e);
        }
    }

    public User updateUser(User user) {
        try {
            entityManager.getTransaction().begin();
            User updatedUser = entityManager.merge(user);
            entityManager.getTransaction().commit();
            return updatedUser;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    public void deleteUser(Long id) {
        try {
            entityManager.getTransaction().begin();
            User user = getUserById(id);
            if (user != null) {
                entityManager.remove(user);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir usuário", e);
        }
    }
}
