package com.backend.management.repository;

import com.backend.management.model.Order;
import com.backend.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByTenant(User user);
    Order findByIdAndTenant(Long id, User tenant);
}
