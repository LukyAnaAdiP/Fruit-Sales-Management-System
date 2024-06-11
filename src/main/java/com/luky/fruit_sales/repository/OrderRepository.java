package com.luky.fruit_sales.repository;

import com.luky.fruit_sales.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
