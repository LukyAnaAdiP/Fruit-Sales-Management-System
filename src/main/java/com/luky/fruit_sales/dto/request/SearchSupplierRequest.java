package com.luky.fruit_sales.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchSupplierRequest {
    private String name;
    private String contactInfo;
    private String address;
}
