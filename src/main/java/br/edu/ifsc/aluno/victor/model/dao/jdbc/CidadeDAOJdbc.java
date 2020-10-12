package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.model.Cidade;
import br.edu.ifsc.aluno.victor.model.dao.CidadeDAO;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
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
public class CidadeDAOJdbc implements CidadeDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Cidade object) {
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
    public Optional<Cidade> findById(Integer id) {
        EntityManager em = getEM();
        Cidade cidade = em.find(Cidade.class, id);
        if (Objects.isNull(cidade)) {
            return Optional.empty();
        }
        return Optional.of(cidade);
    }

    @Override
    public List<Cidade> findAll(){
        EntityManager em = getEM();
        List<Cidade> cidades;
        cidades = em.createQuery("SELECT c FROM Cidade c").getResultList();
        return cidades;
    }

    @Override
    public void update(Cidade object) {
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
            Cidade object = em.find(Cidade.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Cidade findByDescricao(String descricao) {
        EntityManager em = getEM();
        List<Cidade> cidades = em.createQuery(String.format("SELECT c FROM Cidade c WHERE c.descricao = '%s'", descricao)).getResultList();
        return cidades.get(0);
    }
}
