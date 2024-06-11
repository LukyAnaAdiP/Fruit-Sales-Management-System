package com.luky.fruit_sales.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luky.fruit_sales.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = ConstantTable.ORDER)
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderDetail> orderDetails;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", updatable = false)
    private Date orderDate;

    @Column(name = "total_amount")
    private Long totalAmount;

}
