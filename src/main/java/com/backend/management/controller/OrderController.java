package com.backend.management.controller;

import com.backend.management.model.Order;
import com.backend.management.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/orders")
    public List<Order> listOrder(@RequestParam(name = "tenant") String tenantName) {
        return orderService.listByTenant(tenantName);
    }

    @GetMapping(value = "/orders/id")
    public Order getOrder(
            @RequestParam(name = "order_id") Long OrderId,
            @RequestParam(name = "tenant") String tenantName) {
        return orderService.findByIdAndTenant(OrderId, tenantName);
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order order) {
        orderService.add(order);
    }

    @DeleteMapping("/orders")
    public void deleteOrder(
            @RequestParam(name = "order_id") Long OrderId,
            @RequestParam(name = "tenant") String tenantName) {
        orderService.delete(OrderId, tenantName);
    }

}
