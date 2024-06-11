package com.luky.fruit_sales.service;

import com.luky.fruit_sales.dto.request.OrderRequest;
import com.luky.fruit_sales.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);

    List<OrderResponse> getAll();
}
