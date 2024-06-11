package com.luky.fruit_sales.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryResponse {
    private String id;
    private String supplierId;
    private String fruitId;
    private Integer qty;
    private Date deliveryDate;
}
