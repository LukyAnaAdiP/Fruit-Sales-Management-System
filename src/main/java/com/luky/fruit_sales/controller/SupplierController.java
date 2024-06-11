package com.luky.fruit_sales.controller;

import com.luky.fruit_sales.constant.APIUrl;
import com.luky.fruit_sales.constant.ResponseMessage;
import com.luky.fruit_sales.dto.request.SearchCustomerRequest;
import com.luky.fruit_sales.dto.request.SearchSupplierRequest;
import com.luky.fruit_sales.dto.response.CommonResponse;
import com.luky.fruit_sales.entity.Customer;
import com.luky.fruit_sales.entity.Supplier;
import com.luky.fruit_sales.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.SUPPLIER_API)
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<CommonResponse<Supplier>> createNewSupplier(@RequestBody Supplier supplier) {
        Supplier newSupplier = supplierService.create(supplier);
        CommonResponse<Supplier> response = CommonResponse.<Supplier>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessage.SUCCESS_SAVE_DATA)
                .data(newSupplier)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Supplier>> getSupplierById(@PathVariable String id) {
        Supplier supplierById = supplierService.getById(id);
        CommonResponse<Supplier> response = CommonResponse.<Supplier>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(supplierById)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Supplier>>> getAllSupplier(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "contact_info", required = false) String contactInfo,
            @RequestParam(name = "address", required = false) String address
    ) {
        SearchSupplierRequest searchSupplierRequest = SearchSupplierRequest.builder()
                .name(name)
                .contactInfo(contactInfo)
                .address(address)
                .build();

        List<Supplier> supplierList = supplierService.getAll(searchSupplierRequest);
        CommonResponse<List<Supplier>> response = CommonResponse.<List<Supplier>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_GET_DATA)
                .data(supplierList)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Supplier>> updateSupplier(@RequestBody Supplier supplier) {
        Supplier update = supplierService.update(supplier);
        CommonResponse<Supplier> response = CommonResponse.<Supplier>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .data(update)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<String>> deleteById(@PathVariable String id) {
        supplierService.deleteById(id);
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
        supplierService.updateAddressById(id, address);
        CommonResponse<String> response = CommonResponse.<String>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessage.SUCCESS_UPDATE_DATA)
                .build();
        return ResponseEntity.ok(response);
    }

}
