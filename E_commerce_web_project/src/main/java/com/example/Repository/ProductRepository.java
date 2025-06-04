// com.example.Repository.ProductRepository.java
package com.example.Repository;

import com.example.Entity.Product;
import com.example.Entity.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findBySellerEmail(String email);

	Optional<User> findAllById(Long productId);
}
