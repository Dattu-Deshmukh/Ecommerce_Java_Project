// com.example.Service.ProductService.java
package com.example.Service;

import com.example.Entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(String id);
    Product saveProduct(Product product);
    void deleteProduct(String id);
}
