package com.luky.fruit_sales.dto.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailRequest {
    private String orderId;
    private String fruitId;
    private Integer qty;
    private Long price;
}
