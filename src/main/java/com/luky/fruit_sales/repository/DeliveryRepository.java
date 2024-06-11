package com.luky.fruit_sales.repository;

import com.luky.fruit_sales.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
}
