package com.example.products;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Service
public class ProductService {
    Hashtable<Long, Product> products = new Hashtable<>();

    public ProductService() {
    }

    public void seed() {
        products.put(1L, new Product(1L, "Product 1", "Category 1", 29.99F, "Description 1"));
        products.put(2L, new Product(2L, "Product 2", "Category 2", 14.99F, "Description 2"));
        products.put(3L, new Product(3L, "Product 3", "Category 3", 22.99F, "Description 3"));
        products.put(4L, new Product(4L, "Product 4", "Category 4", 22.99F, "Description 4"));
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProductById(long productId) {
        return products.get(productId);
    }

    public void updateProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void removeProductById(long productId) {
        products.remove(productId);
    }
}
