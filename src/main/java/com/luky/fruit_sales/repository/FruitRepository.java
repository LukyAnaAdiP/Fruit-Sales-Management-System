package com.luky.fruit_sales.repository;

import com.luky.fruit_sales.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, String>, JpaSpecificationExecutor<Fruit> {
    List<Fruit> findAllByNameLike(String name);
}
