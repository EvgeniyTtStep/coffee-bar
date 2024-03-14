package com.example.coffeebar.service;


import com.example.coffeebar.entity.Order;
import com.example.coffeebar.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private OrderRepository orderRepository;


    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public void save(Order order) {
        if (order != null) {
            orderRepository.save(order);
        } else {
            System.out.println("Order is empty");
        }
    }


    public Order findById(Long idOrder) {
        return orderRepository.findById(idOrder).get();
    }
}
