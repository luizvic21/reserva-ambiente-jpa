package br.edu.ifsc.aluno.victor.model.dao.jdbc;

import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.model.dao.BlocoDAO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlocoDAOJdbc implements BlocoDAO {

    EntityManager entityManager;

    public EntityManager getEM() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("reserva-ambiente");
        if (Objects.isNull(entityManager)) {
            entityManager = emf.createEntityManager();
        }
        return emf.createEntityManager();
    }

    @Override
    public void create(Bloco object) {
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
    public Optional<Bloco> findById(Integer id) {
        EntityManager em = getEM();
        Bloco bloco = em.find(Bloco.class, id);
        if (Objects.isNull(bloco)) {
            return Optional.empty();
        }
        return Optional.of(bloco);
    }

    @Override
    public List<Bloco> findAll(){
        EntityManager em = getEM();
//        List<Object> objects = em.createQuery("SELECT b FROM Bloco b ").getResultList();
//        List<Bloco> blocos = new ArrayList<>();
//        for (Object object : objects) {
//            Bloco bloco = (Bloco) object;
//            blocos.add(new Bloco((Bloco) object));
//        }
        List<Bloco> blocos = em.createQuery("SELECT b FROM Bloco b ").getResultList();
        return blocos;
    }

    @Override
    public void update(Bloco object) {
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
            Bloco object = em.find(Bloco.class, id);
            em.remove(object);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Bloco findByDescricao(String descricao) {
        EntityManager em = getEM();
        List<Bloco> blocos = em.createQuery(String.format("SELECT b FROM Bloco b WHERE b.descricao = '%s'", descricao)).getResultList();
        return blocos.get(0);
    }
}
