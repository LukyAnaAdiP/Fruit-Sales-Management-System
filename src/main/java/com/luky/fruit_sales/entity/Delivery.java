package com.luky.fruit_sales.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luky.fruit_sales.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.DELIVERY)
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "fruit_id", nullable = false)
    private Fruit fruit;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "delivery_date", updatable = false)
    private Date deliveryDate;

    @Column(name = "quantity", nullable = false)
    private Integer qyt;

}
