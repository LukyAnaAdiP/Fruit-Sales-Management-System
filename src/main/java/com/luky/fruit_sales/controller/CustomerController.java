package com.luky.fruit_sales.controller;

import com.luky.fruit_sales.constant.APIUrl;
import com.luky.fruit_sales.constant.ResponseMessage;
import com.luky.fruit_sales.dto.request.SearchCustomerRequest;
import com.luky.fruit_sales.dto.response.CommonResponse;
import com.luky.fruit_sales.entity.Customer;
import com.luky.fruit_sales.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.CUSTOMER_API)
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CommonResponse<Customer>> createNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newCustomer)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer customerById = customerService.getById(id);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customerById)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Customer>>> getAllCustomer(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address
    ) {
        SearchCustomerRequest searchCustomerRequest = SearchCustomerRequest.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .address(address)
                .build();

        List<Customer> customerList = customerService.getAll(searchCustomerRequest);
        CommonResponse<List<Customer>> response = CommonResponse.<List<Customer>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(customerList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Customer>> updateCustomer(@RequestBody Customer customer) {
        Customer update = customerService.update(customer);
        CommonResponse<Customer> response = CommonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(update)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> deleteById(@PathVariable String id) {
        customerService.deleteById(id);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_DELETE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> updateAddress(
            @PathVariable String id,
            @RequestParam(name = "address") String address
    ) {
        customerService.updateAddressById(id, address);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

}
