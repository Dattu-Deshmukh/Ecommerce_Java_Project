package com.example.Repository;

import com.example.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findBySellerEmail(String email);
    List<Product> findByCategory(String category);
    List<Product> findByNameRegexIgnoreCase(String regex);
}
