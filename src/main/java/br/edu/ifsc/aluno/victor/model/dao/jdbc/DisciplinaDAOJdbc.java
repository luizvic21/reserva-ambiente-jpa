package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.model.CursoDisciplina;
import br.edu.ifsc.aluno.victor.model.Disciplina;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import br.edu.ifsc.aluno.victor.model.dao.DisciplinaDAO;
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
public class DisciplinaDAOJdbc implements DisciplinaDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Disciplina object) {
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
    public Optional<Disciplina> findById(Integer id) {
        EntityManager em = getEM();
        Disciplina disciplina = em.find(Disciplina.class, id);
        if (Objects.isNull(disciplina)) {
            return Optional.empty();
        }
        return Optional.of(disciplina);
    }

    @Override
    public List<Disciplina> findAll(){
        EntityManager em = getEM();
        List<Disciplina> disciplinas;
        disciplinas = em.createQuery("SELECT d FROM Disciplina d ").getResultList();
        return disciplinas;
    }

    @Override
    public void update(Disciplina object) {
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
            Disciplina object = em.find(Disciplina.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Integer createReturnId(Disciplina disciplina) {
        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(disciplina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }

        return disciplina.getId();
    }
}
