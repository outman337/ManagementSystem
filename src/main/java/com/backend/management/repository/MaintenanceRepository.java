package com.backend.management.repository;

import com.backend.management.model.Order;
import com.backend.management.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Order, Long> {
    List<Order> findByTenant(User tenant);

}
