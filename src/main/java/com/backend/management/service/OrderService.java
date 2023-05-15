package com.backend.management.service;

import com.backend.management.exception.OrderNotExistException;
import com.backend.management.model.Order;
import com.backend.management.model.User;
import com.backend.management.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public List<Order> listByTenant(String username){
        return orderRepository.findByTenant(new User.Builder().setUsername(username).build());
    }
    public Order findByIdAndTenant(Long orderId, String username) throws OrderNotExistException {
        Order order = orderRepository.findByIdAndTenant(orderId,new User.Builder().setUsername(username).build());
        if(order == null){
            throw new OrderNotExistException("Order DNE");
        }
        return order;
    }
    public void add(Order order){
        orderRepository.save(order);
    }
    @Transactional
    public void delete(Long orderId, String username)throws OrderNotExistException{
        Order order = orderRepository.findByIdAndTenant(orderId,new User.Builder().setUsername(username).build());
        if(order == null){
            throw new OrderNotExistException("Order DNE");
        }
        orderRepository.deleteById(orderId);
    }
}
