package ru.persistence;

import ru.persistence.Product;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ProductRepository{

    private final EntityManager em;
    public ProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    public List<Product> findAll() {
        em.getTransaction().begin();
        List<Product> list = em.createNamedQuery("Product.findAll", Product.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

    public List<Product> findAllSortedByName() {
        em.getTransaction().begin();
        List<Product> list = em.createNamedQuery("Product.findAllSortedByName", Product.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

    public void saveOrUpdate(Product product) {
        em.getTransaction().begin();
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
        em.getTransaction().commit();
    }

    public Product findById(Long id) {
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.getTransaction().commit();
        return product;
    }

    public void deleteById(Long id) {
        em.getTransaction().begin();
        em.createNamedQuery("Product.deleteById")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }
}
