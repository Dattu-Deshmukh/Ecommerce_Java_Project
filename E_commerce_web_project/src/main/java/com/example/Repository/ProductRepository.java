package com.example.Repository;

import com.example.Entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Find products by the seller's email
    List<Product> findBySellerEmail(String email);

    // Find products by category
    List<Product> findByCategory(String category);

    // Case-insensitive name search using regex
    List<Product> findByNameRegexIgnoreCase(String regex);

}
