package com.luky.fruit_sales.controller;

import com.luky.fruit_sales.constant.APIUrl;
import com.luky.fruit_sales.dto.request.DeliveryRequest;
import com.luky.fruit_sales.dto.response.DeliveryResponse;
import com.luky.fruit_sales.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.DELIVERY_API)
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping
    public DeliveryResponse createNewDelivery(
            @RequestBody DeliveryRequest deliveryRequest
    ) {
        return deliveryService.create(deliveryRequest);
    }

    @GetMapping
    public List<DeliveryResponse> getAllDelivery() {
        return deliveryService.getAll();
    }
}
