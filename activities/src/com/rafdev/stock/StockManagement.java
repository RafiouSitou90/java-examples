package com.rafdev.stock;

import com.rafdev.stock.model.Product;
import com.rafdev.stock.service.impl.ProductServiceImpl;

public class StockManagement {

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();

        Product product_1 = new Product(1L, "HP-20", 2000, 5);
        Product product_2 = new Product(2L, "DELL-50", 1000, 10);

        productService.createProduct(product_1);
        productService.createProduct(product_2);

        System.out.println("PRODUCT LIST : " + productService.getAllProduct());

        System.out.println("FIND BY ID : " + productService.getOneProductById(1L));

//        boolean isDeleted = productService.deleteProduct(1L);
//        System.out.println("IS DELETED ? " + isDeleted);
//        System.out.println("PRODUCT LIST AFTER REMOVE : " + productService.getAllProduct());
    }
}
