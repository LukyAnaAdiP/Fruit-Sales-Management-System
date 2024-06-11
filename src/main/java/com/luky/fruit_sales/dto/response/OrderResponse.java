package com.luky.fruit_sales.dto.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private String id;
    private String customerId;
    private Date orderDate;
    private Long totalAmount;
    private List<OrderDetailResponse> orderDetails;
}
