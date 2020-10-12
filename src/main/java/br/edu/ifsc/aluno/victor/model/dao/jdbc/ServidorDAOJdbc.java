package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.enuns.EnumTipoServidor;
import br.edu.ifsc.aluno.victor.model.*;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import br.edu.ifsc.aluno.victor.model.dao.ServidorDAO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServidorDAOJdbc implements ServidorDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Servidor object) {
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
    public Optional<Servidor> findById(Integer id) {
        EntityManager em = getEM();
        Servidor servidor = em.find(Servidor.class, id);
        if (Objects.isNull(servidor)) {
            return Optional.empty();
        }
        return Optional.of(servidor);
    }

    @Override
    public List<Servidor> findAll(){
        EntityManager em = getEM();
        List<Servidor> servidores;
        servidores = em.createQuery("SELECT s FROM Servidor s ").getResultList();
        return servidores;
    }

    @Override
    public void update(Servidor object) {
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
            Servidor object = em.find(Servidor.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Servidor findByDescricao(String descricao) {
        EntityManager em = getEM();
        List<Servidor> servidores = em.createQuery(String.format("SELECT s FROM Servidor s WHERE s.nome = '%s'", descricao)).getResultList();
        return servidores.get(0);
    }

    @Override
    public List<Servidor> findByTipo(EnumTipoServidor tipoServidor) {
        EntityManager  em = getEM();
        Query query = em.createQuery("SELECT s FROM Servidor s WHERE s.tipoServidor = :tipoServidor");
        query.setParameter("tipoServidor", tipoServidor);
        List<Servidor> servidores = query.getResultList();
        return servidores;
    }
}
