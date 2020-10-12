package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.enuns.EnumModalidade;
import br.edu.ifsc.aluno.victor.enuns.EnumPeriodo;
import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.model.Curso;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import br.edu.ifsc.aluno.victor.model.dao.CursoDAO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursoDAOJdbc implements CursoDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Curso object) {
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
    public Optional<Curso> findById(Integer id) {
        EntityManager em = getEM();
        Curso curso = em.find(Curso.class, id);
        if (Objects.isNull(curso)) {
            return Optional.empty();
        }
        return Optional.of(curso);
    }

    @Override
    public List<Curso> findAll(){
        EntityManager em = getEM();
        List<Curso> cursos;
        cursos = em.createQuery("SELECT c FROM Curso c ").getResultList();
        return cursos;
    }

    @Override
    public void update(Curso object) {
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
            Curso object = em.find(Curso.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Curso findByDescricao(String descricao) {
        EntityManager em = getEM();
        List<Curso> cursos = em.createQuery(String.format("SELECT c FROM Curso c WHERE c.descricao = '%s'", descricao)).getResultList();
        return cursos.get(0);
    }
}
