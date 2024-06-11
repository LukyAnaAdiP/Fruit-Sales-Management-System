package com.luky.fruit_sales.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRequest {
    private String fruitId;
    private String supplierId;
    private Integer qty;
}
