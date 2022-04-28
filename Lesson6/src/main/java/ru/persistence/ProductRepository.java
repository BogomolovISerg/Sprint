package ru.persistence;

import ru.persistence.entities.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProductRepository{

    private EntityManager em;
    public ProductRepository(EntityManager em) {
        this.em = em;
    }

    public List<Product> listAll() {
        em.getTransaction().begin();
        List<Product> list = em.createNamedQuery("Product.findAll", Product.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

    public void addProduct(Product product) {
        em.getTransaction().begin();
        if (product.getId() == null) {
            em.persist(product);
        } else{
            em.merge(product);
        }
        em.getTransaction().commit();
    }

    public Product findProduct(Integer id) {
        em.getTransaction().begin();
        Product product =  em.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        em.getTransaction().commit();
        return product;
    }

    public void deleteProduct(Integer id) {
        em.getTransaction().begin();
        em.createNamedQuery("Product.deleteById")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
    }
}
