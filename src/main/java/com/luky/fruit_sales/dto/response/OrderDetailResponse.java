package com.luky.fruit_sales.dto.response;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private String id;
    private String orderId;
    private String fruitId;
    private Integer qty;
    private Long price;
}
