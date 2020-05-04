package br.jss.warehouse.dao;

import br.jss.warehouse.model.Product;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateful
public class ProductDAO {

    @PersistenceContext(name = "warehouse_pu")
    private EntityManager entityManager;

    public Product save(Product product) {
        return entityManager.merge(product);
    }

    public Product getById(Short id) {
        return entityManager.find(Product.class, id);
    }

    public Product getByName(String name) {
;
        return entityManager.createQuery("select x from product x where x.name = :name", Product.class)
                .setParameter("name", name).getSingleResult();
    }

    public List<Product> getAll() {
        return entityManager.createQuery("select c from product c ", Product.class)
                .setMaxResults(30).getResultList();
    }

    public void remove(Short id) throws EntityNotFoundException {
        Product product = entityManager.getReference(Product.class, id);
        entityManager.remove(product);
    }
}
