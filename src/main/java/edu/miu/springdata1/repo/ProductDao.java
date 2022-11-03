package edu.miu.springdata1.repo;


import edu.miu.springdata1.entity.Product;

import java.util.List;

public interface ProductDao {

    List<Product> filterProductsByPriceAndColor(Double price, String color);
}
