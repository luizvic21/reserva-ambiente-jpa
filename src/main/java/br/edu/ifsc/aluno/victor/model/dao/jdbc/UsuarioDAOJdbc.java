package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.model.Cidade;
import br.edu.ifsc.aluno.victor.model.Endereco;
import br.edu.ifsc.aluno.victor.model.Usuario;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import br.edu.ifsc.aluno.victor.model.dao.UsuarioDAO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioDAOJdbc implements UsuarioDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Usuario object) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Optional<Usuario> findById(Integer id) {
        EntityManager em = getEM();
        Usuario usuario = em.find(Usuario.class, id);
        if (Objects.isNull(usuario)) {
            return Optional.empty();
        }
        return Optional.of(usuario);
    }

    @Override
    public List<Usuario> findAll(){
        EntityManager em = getEM();
        List<Usuario> usuarios;
        usuarios = em.createQuery("SELECT u FROM Usuario u ").getResultList();
        return usuarios;
    }

    @Override
    public void update(Usuario object) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            Usuario object = em.find(Usuario.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Optional<Usuario> logar(String username, String senha) {
        EntityManager em = getEM();
        List<Usuario> usuarios;
        usuarios = em.createQuery(String.format("SELECT u FROM Usuario u WHERE u.username = '%s' AND u.senha = '%s'", username, senha)).getResultList();
        if (usuarios.isEmpty()) {
            Optional.empty();
        }
        return Optional.of(usuarios.get(0));
    }
}
