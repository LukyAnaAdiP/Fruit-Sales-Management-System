package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.entity.OrderDetail;
import com.luky.fruit_sales.repository.OrderDetailRepository;
import com.luky.fruit_sales.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> createBulk(List<OrderDetail> orderDetails) {
        return orderDetailRepository.saveAllAndFlush(orderDetails);
    }
}
