package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.dto.request.OrderRequest;
import com.luky.fruit_sales.dto.response.OrderDetailResponse;
import com.luky.fruit_sales.dto.response.OrderResponse;
import com.luky.fruit_sales.entity.Customer;
import com.luky.fruit_sales.entity.Fruit;
import com.luky.fruit_sales.entity.Order;
import com.luky.fruit_sales.entity.OrderDetail;
import com.luky.fruit_sales.repository.OrderDetailRepository;
import com.luky.fruit_sales.repository.OrderRepository;
import com.luky.fruit_sales.service.CustomerService;
import com.luky.fruit_sales.service.FruitService;
import com.luky.fruit_sales.service.OrderDetailService;
import com.luky.fruit_sales.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final CustomerService customerService;
    private final FruitService fruitService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderResponse create(OrderRequest orderRequest) {
        Customer customer = customerService.getById(orderRequest.getCustomerId());
        Order order = Order.builder()
                .customer(customer)
                .totalAmount(orderRequest.getTotalAmount())
                .orderDate(new Date())
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetail> orderDetail = orderRequest.getOrderDetails().stream()
                .map(detailRequest -> {
                    Fruit fruit = fruitService.getById(detailRequest.getFruitId());
                    return OrderDetail.builder()
                            .order(order)
                            .fruit(fruit)
                            .qty(detailRequest.getQty())
                            .price(detailRequest.getPrice())
                            .build();
                }).toList();
        orderDetailService.createBulk(orderDetail);
        order.setOrderDetails(orderDetail);

        List<OrderDetailResponse> orderDetailResponse = orderDetail.stream()
                .map(detail -> OrderDetailResponse.builder()
                        .id(detail.getId())
                        .orderId(detail.getOrder().getId())
                        .fruitId(detail.getFruit().getId())
                        .qty(detail.getQty())
                        .price(detail.getPrice())
                        .build()).toList();
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .orderDate(order.getOrderDate())
                .totalAmount(order.getTotalAmount())
                .orderDetails(orderDetailResponse)
                .build();
    }

    @Override
    public List<OrderResponse> getAll() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(order -> {
            List<OrderDetailResponse> orderDetailResponse = order.getOrderDetails().stream().map(detail -> OrderDetailResponse.builder()
                    .id(detail.getId())
                    .orderId(detail.getOrder().getId())
                    .fruitId(detail.getFruit().getId())
                    .price(detail.getPrice())
                    .qty(detail.getQty())
                    .build()).toList();

            return OrderResponse.builder()
                    .id(order.getId())
                    .customerId(order.getCustomer().getId())
                    .orderDate(order.getOrderDate())
                    .totalAmount(order.getTotalAmount())
                    .orderDetails(orderDetailResponse)
                    .build();
        }).toList();
    }
}
