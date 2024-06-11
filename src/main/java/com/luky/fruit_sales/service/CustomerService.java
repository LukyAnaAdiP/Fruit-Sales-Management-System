package com.luky.fruit_sales.service;

import com.luky.fruit_sales.dto.request.SearchCustomerRequest;
import com.luky.fruit_sales.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer getById(String id);

    List<Customer> getAll(SearchCustomerRequest searchCustomerRequest);

    Customer update(Customer customer);

    void deleteById(String id);

    void updateAddressById(String id, String address);
}
