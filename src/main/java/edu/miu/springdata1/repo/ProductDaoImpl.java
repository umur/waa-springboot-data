package edu.miu.springdata1.repo;

import edu.miu.springdata1.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> filterProductsByPriceAndColor(Double price, String color) {
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query= builder.createQuery(Product.class);

        Root<Product> root= query.from(Product.class);
        List<Predicate> predicates= new ArrayList<Predicate>();

        if(price!=null){
            predicates.add(builder.greaterThan(root.get("price"), price));
        }

        if(color!=null){
            predicates.add(builder.equal(root.get("color"), color));
        }

        query.where(predicates.toArray(new Predicate[0]));

       var result= entityManager.createQuery(query).getResultList();

        return result;
    }

}
