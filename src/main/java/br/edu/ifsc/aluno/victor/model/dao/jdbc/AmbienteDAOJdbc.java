package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.model.Ambiente;
import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.model.dao.AmbienteDAO;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AmbienteDAOJdbc implements AmbienteDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Ambiente object) {
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
    public Optional<Ambiente> findById(Integer id) {
        EntityManager em = getEM();
        Ambiente ambiente = em.find(Ambiente.class, id);
        if (Objects.isNull(ambiente)) {
            return Optional.empty();
        }
        return Optional.of(ambiente);
    }

    @Override
    public List<Ambiente> findAll(){
        EntityManager em = getEM();
        List<Ambiente> ambientes;
        ambientes = em.createQuery("SELECT a FROM Ambiente a ").getResultList();
        return ambientes;
    }

    @Override
    public void update(Ambiente object) {
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
            Ambiente object = em.find(Ambiente.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Ambiente> findByBlocoId(Integer blocoId) {
        return null;
    }
}
