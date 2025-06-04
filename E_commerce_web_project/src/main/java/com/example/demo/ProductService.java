package com.example.demo;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public List<Product> getProductsBySeller(String email) {
        return repo.findBySellerEmail(email);
    }

    public Product getProductById(String id) {
        return repo.findById(id).orElse(null);
    }
}
