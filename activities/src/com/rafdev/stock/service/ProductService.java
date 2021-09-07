package com.rafdev.stock.service;

import com.rafdev.stock.model.Product;

import java.util.List;
import java.util.Set;

public interface ProductService {

    void createProduct(Product product);

    void updateProduct(Product product);

    boolean deleteProduct(Long id);

    List<Product> getAllProduct();

    Product getOneProductById(Long id);

    boolean existById(Long id);
}
