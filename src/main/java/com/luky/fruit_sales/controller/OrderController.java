package com.luky.fruit_sales.controller;

import com.luky.fruit_sales.constant.APIUrl;
import com.luky.fruit_sales.dto.request.OrderRequest;
import com.luky.fruit_sales.dto.response.OrderResponse;
import com.luky.fruit_sales.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.ORDER_API)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderResponse createNewOrder(
            @RequestBody OrderRequest orderRequest
    ) {
        return orderService.create(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> getAllOrder() {
        return orderService.getAll();
    }
}
