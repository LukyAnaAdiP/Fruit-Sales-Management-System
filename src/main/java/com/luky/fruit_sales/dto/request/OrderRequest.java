package com.luky.fruit_sales.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private String customerId;
    private List<OrderDetailRequest> orderDetails;
    private Long totalAmount;
}
