package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.dto.request.DeliveryRequest;
import com.luky.fruit_sales.dto.response.DeliveryResponse;
import com.luky.fruit_sales.entity.Delivery;
import com.luky.fruit_sales.entity.Fruit;
import com.luky.fruit_sales.entity.Supplier;
import com.luky.fruit_sales.repository.DeliveryRepository;
import com.luky.fruit_sales.service.DeliveryService;
import com.luky.fruit_sales.service.FruitService;
import com.luky.fruit_sales.service.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final FruitService fruitService;
    private final SupplierService supplierService;

    @Override
    public DeliveryResponse create(DeliveryRequest deliveryRequest) {
        Supplier supplier = supplierService.getById(deliveryRequest.getSupplierId());
        Fruit fruit = fruitService.getById(deliveryRequest.getFruitId());
        Delivery delivery = Delivery.builder()
                .supplier(supplier)
                .fruit(fruit)
                .qyt(deliveryRequest.getQty())
                .deliveryDate(new Date())
                .build();
        deliveryRepository.saveAndFlush(delivery);
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .supplierId(delivery.getSupplier().getId())
                .deliveryDate(delivery.getDeliveryDate())
                .qty(delivery.getQyt())
                .fruitId(delivery.getFruit().getId())
                .build();
    }

    @Override
    public List<DeliveryResponse> getAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        return deliveries.stream().map(delivery -> DeliveryResponse.builder()
                .id(delivery.getId())
                .supplierId(delivery.getSupplier().getId())
                .fruitId(delivery.getFruit().getId())
                .qty(delivery.getQyt())
                .deliveryDate(delivery.getDeliveryDate())
                .build()).toList();
    }
}
