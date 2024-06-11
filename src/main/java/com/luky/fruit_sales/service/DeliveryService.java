package com.luky.fruit_sales.service;

import com.luky.fruit_sales.dto.request.DeliveryRequest;
import com.luky.fruit_sales.dto.response.DeliveryResponse;
import com.luky.fruit_sales.entity.Delivery;

import java.util.List;

public interface DeliveryService {
    DeliveryResponse create(DeliveryRequest deliveryRequest);

    List<DeliveryResponse> getAll();
}
