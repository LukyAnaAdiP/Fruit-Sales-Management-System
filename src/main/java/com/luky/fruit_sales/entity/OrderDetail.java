package com.luky.fruit_sales.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luky.fruit_sales.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.ORDER_DETAIL)
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "fruit_id", nullable = false)
    private Fruit fruit;

    @Column(name = "quantity", nullable = false)
    private Integer qty;

    @Column(name = "price", nullable = false)
    private Long price;

}
