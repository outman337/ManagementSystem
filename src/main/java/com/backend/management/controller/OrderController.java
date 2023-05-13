package com.backend.management.controller;

import com.backend.management.model.Order;
import com.backend.management.model.User;
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
    @GetMapping(value = "/orders/orderClaimed")
    public List<Order> listOrderClaimed(@RequestBody String providerName) {
        return orderService.listByProvider(providerName);
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

    @PostMapping("/orders/claim")
    public void claimTask(@RequestBody String username, @RequestParam(name = "order_id") Long OrderId) {
        orderService.claimTask(OrderId,username);
    }

    @PostMapping("/orders/complete")
    public void completeTask(@RequestParam(name = "order_id") Long OrderId) {
        orderService.completeTask(OrderId);
    }


    @DeleteMapping("/orders")
    public void deleteOrder(
            @RequestParam(name = "order_id") Long OrderId,
            @RequestParam(name = "tenant") String tenantName) {
        orderService.delete(OrderId, tenantName);
    }

    @GetMapping(value = "/orders/findAll")
    public List<Order> listOfAllOrder(){
        return orderService.listOfAll();
    }


    @GetMapping(value = "/orders/findAllUnclaimed")
    public List<Order> listOfAllUnclaimed(){
        return orderService.listOfUnclaimed();
    }
}
