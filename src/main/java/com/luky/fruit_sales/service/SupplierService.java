package com.luky.fruit_sales.service;

import com.luky.fruit_sales.dto.request.SearchSupplierRequest;
import com.luky.fruit_sales.entity.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier create(Supplier supplier);

    Supplier getById(String id);

    List<Supplier> getAll(SearchSupplierRequest searchSupplierRequest);

    Supplier update(Supplier supplier);

    void deleteById(String id);

    void updateAddressById(String id, String address);
}
