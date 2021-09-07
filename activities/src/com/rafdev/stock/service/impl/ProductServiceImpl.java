package com.rafdev.stock.service.impl;

import com.rafdev.stock.database.Database;
import com.rafdev.stock.model.Product;
import com.rafdev.stock.service.ProductService;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final Database database = new Database();

    public ProductServiceImpl() {
    }

    @Override
    public void createProduct(Product product) {
        database.getProductList().add(product);
    }

    @Override
    public void updateProduct(Product product) {
    }

    @Override
    public boolean deleteProduct(Long id) {
        return database.getProductList().removeIf(product -> product.getId().equals(id));
    }

    @Override
    public List<Product> getAllProduct() {
        return database.getProductList();
    }

    @Override
    public Product getOneProductById(Long id) {
        Product[] products = getAllProduct().toArray(new Product[0]);

        int i = 0;
        boolean found = false;
        Product product = null;
        while (i < products.length && !found) {
            if (Objects.equals(products[i].getId(), id)) {
                product = products[i];
                found = true;
            } else {
                i++;
            }
        }

        return product;
    }

    @Override
    public boolean existById(Long id) {
        Product[] products = getAllProduct().toArray(new Product[0]);
        int i = 0;
        boolean found = false;
        while (i < products.length && !found) {
            if (Objects.equals(products[i].getId(), id)) {
                found = true;
            } else {
                i++;
            }
        }

        return found;
    }
}
