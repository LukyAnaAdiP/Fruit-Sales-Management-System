package com.luky.fruit_sales.service;

import com.luky.fruit_sales.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> createBulk(List<OrderDetail> orderDetails);
}
