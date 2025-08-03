package com.example.Repository;

import com.example.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    
    // Retrieve all orders for a given user ID
    List<Order> findByUserId(String userId);

}
