package ru.persistence;

import ru.persistence.entities.Сlients;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class СlientsRepository{

    private EntityManager em;

    public СlientsRepository(EntityManager em) {
        this.em = em;
    }

    public List<Сlients> findAll() {
        em.getTransaction().begin();
        List<Сlients> list = em.createNamedQuery("User.findAll", Сlients.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

    public void saveOrUpdate(Сlients user) {
        em.getTransaction().begin();
        if (user.getId() == null) {
            em.persist(user);
        }
        em.merge(user);
        em.getTransaction().commit();
    }

    public Сlients findById(Long id) {
        em.getTransaction().begin();
        Сlients user = em.find(Сlients.class, id); // CRUD - read
        em.getTransaction().commit();
        return user;
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        em.createNamedQuery("User.deleteById")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }

}
