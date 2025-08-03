package com.example.demo;

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

    /**
     * Save or update a product.
     * @param product Product entity to save.
     * @return saved product
     */
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    /**
     * Retrieve all products.
     * @return list of all products
     */
    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    /**
     * Get products listed by a specific seller.
     * @param email seller's email
     * @return list of products by seller
     */
    public List<Product> getProductsBySeller(String email) {
        return repo.findBySellerEmail(email);
    }

    /**
     * Get a single product by its ID.
     * @param id product ID
     * @return Optional of product
     */
    public Optional<Product> getProductById(String id) {
        return repo.findById(id);
    }

    /**
     * Delete a product by its ID.
     * @param id product ID to delete
     */
    public void deleteProductById(String id) {
        repo.deleteById(id);
    }
}
