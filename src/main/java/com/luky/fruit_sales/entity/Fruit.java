package com.luky.fruit_sales.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luky.fruit_sales.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.FRUIT)
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price_per_kg")
    private Long price;

    @Column(name = "stock_quantity")
    private Integer stock;

}
