package com.example.Service;

import com.example.Entity.Order;
import com.example.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return orderRepo.findByUserId(userId);
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepo.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public void deleteOrderById(String id) {
        orderRepo.deleteById(id);
    }
}
