package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.dto.request.SearchCustomerRequest;
import com.luky.fruit_sales.entity.Customer;
import com.luky.fruit_sales.repository.CustomerRepository;
import com.luky.fruit_sales.service.CustomerService;
import com.luky.fruit_sales.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Customer> getAll(SearchCustomerRequest searchCustomerRequest) {
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(searchCustomerRequest);
        if (searchCustomerRequest.getName() == null && searchCustomerRequest.getPhone() == null && searchCustomerRequest.getEmail() == null && searchCustomerRequest.getAddress() == null) {
            return customerRepository.findAll();
        }
        return customerRepository.findAll(customerSpecification);
    }

    @Override
    public Customer update(Customer customer) {
        findByIdOrThrowNotFound(customer.getId());
        return customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        customerRepository.delete(customer);
    }

    @Override
    public void updateAddressById(String id, String address) {
        findByIdOrThrowNotFound(id);
        customerRepository.updateAddress(id, address);
    }

    public Customer findByIdOrThrowNotFound(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }
}
