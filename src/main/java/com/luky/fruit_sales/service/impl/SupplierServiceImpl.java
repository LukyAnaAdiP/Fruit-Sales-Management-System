package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.dto.request.SearchSupplierRequest;
import com.luky.fruit_sales.entity.Customer;
import com.luky.fruit_sales.entity.Supplier;
import com.luky.fruit_sales.repository.SupplierRepository;
import com.luky.fruit_sales.service.SupplierService;
import com.luky.fruit_sales.specification.CustomerSpecification;
import com.luky.fruit_sales.specification.SupplierSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Supplier create(Supplier supplier) {
        return supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public Supplier getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Supplier> getAll(SearchSupplierRequest searchSupplierRequest) {
        Specification<Supplier> supplierSpecification = SupplierSpecification.getSpecification(searchSupplierRequest);
        if (searchSupplierRequest.getName() == null && searchSupplierRequest.getContactInfo() == null && searchSupplierRequest.getAddress() == null) {
            return supplierRepository.findAll();
        }
        return supplierRepository.findAll(supplierSpecification);
    }

    @Override
    public Supplier update(Supplier supplier) {
        findByIdOrThrowNotFound(supplier.getId());
        return supplierRepository.saveAndFlush(supplier);
    }

    @Override
    public void deleteById(String id) {
        Supplier supplier = findByIdOrThrowNotFound(id);
        supplierRepository.delete(supplier);
    }

    @Override
    public void updateAddressById(String id, String address) {
        findByIdOrThrowNotFound(id);
        supplierRepository.updateAddress(id, address);
    }

    public Supplier findByIdOrThrowNotFound(String id) {
        return supplierRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "supplier not found"));
    }

}
