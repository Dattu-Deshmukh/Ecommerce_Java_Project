package com.example.Service;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getProductById(String id) {
        return repo.findById(id);
    }

    public void deleteProductById(String id) {
        repo.deleteById(id);
    }

    public List<Product> getProductsByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Product> searchProductsByName(String keyword) {
        return repo.findByNameRegexIgnoreCase(".*" + keyword + ".*");
    }
}
