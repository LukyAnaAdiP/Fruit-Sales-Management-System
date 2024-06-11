package com.luky.fruit_sales.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFruitRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String direction;
    private String name;
}
