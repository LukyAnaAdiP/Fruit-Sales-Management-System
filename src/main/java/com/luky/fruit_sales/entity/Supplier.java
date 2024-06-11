package com.luky.fruit_sales.entity;

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
@Table(name = ConstantTable.SUPPLIER)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "address")
    private String address;
}
