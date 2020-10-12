package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.enuns.EnumModalidade;
import br.edu.ifsc.aluno.victor.enuns.EnumPeriodo;
import br.edu.ifsc.aluno.victor.enuns.EnumTipoServidor;
import br.edu.ifsc.aluno.victor.model.*;
import br.edu.ifsc.aluno.victor.model.dao.ConnectionFactory;
import br.edu.ifsc.aluno.victor.model.dao.CoordenadorDAO;
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
public class CoordenadorDAOJdbc implements CoordenadorDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    @Override
    public void create(Coordenador object) {
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
    public Optional<Coordenador> findById(Integer id) {
        EntityManager em = getEM();
        Coordenador coordenador = em.find(Coordenador.class, id);
        if (Objects.isNull(coordenador)) {
            return Optional.empty();
        }
        return Optional.of(coordenador);
    }

    @Override
    public List<Coordenador> findAll(){
        EntityManager em = getEM();
        List<Coordenador> coordenadors;
        coordenadors = em.createQuery("SELECT c FROM Coordenador c ").getResultList();
        return coordenadors;
    }

    @Override
    public void update(Coordenador object) {
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
            Coordenador object = em.find(Coordenador.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Optional<Coordenador> buscarCoordenadorAtivo(Integer cursoId) {
        EntityManager em = getEM();
        List<Coordenador> coordenadores = em.createQuery(String.format("SELECT c FROM Coordenador c JOIN c.curso cc WHERE cc = %d", cursoId)).getResultList();
        if (coordenadores.isEmpty()) {
            Optional.empty();
        }
        return Optional.of(coordenadores.get(0));
    }
}
