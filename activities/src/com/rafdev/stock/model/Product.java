package com.rafdev.stock.model;

public class Product {
    private Long id;
    private String name;
    private double price;
    private int qtyStock;

    public Product() {
    }

    public Product(Long id, String name, double price, int qtyStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qtyStock = qtyStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQtyStock() {
        return qtyStock;
    }

    public void setQtyStock(int qtyStock) {
        this.qtyStock = qtyStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qtyStock=" + qtyStock +
                '}';
    }
}
