package com.luky.fruit_sales.service;

import com.luky.fruit_sales.dto.request.NewFruitRequest;
import com.luky.fruit_sales.dto.request.SearchFruitRequest;
import com.luky.fruit_sales.entity.Fruit;
import org.springframework.data.domain.Page;

public interface FruitService {
    Fruit create(NewFruitRequest fruitRequest);

    Fruit getById(String id);

    Page<Fruit> getAll(SearchFruitRequest fruitRequest);

    Fruit update(Fruit fruit);

    void deleteById(String id);
}
