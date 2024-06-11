package com.luky.fruit_sales.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCustomerRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
}
