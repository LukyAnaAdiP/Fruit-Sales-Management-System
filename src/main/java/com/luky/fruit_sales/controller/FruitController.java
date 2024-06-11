package com.luky.fruit_sales.controller;

import com.luky.fruit_sales.constant.APIUrl;
import com.luky.fruit_sales.dto.request.NewFruitRequest;
import com.luky.fruit_sales.dto.request.SearchFruitRequest;
import com.luky.fruit_sales.dto.response.CommonResponse;
import com.luky.fruit_sales.dto.response.PagingResponse;
import com.luky.fruit_sales.entity.Fruit;
import com.luky.fruit_sales.service.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.FRUIT_API)
public class FruitController {

    private final FruitService fruitService;

    @PostMapping
    public ResponseEntity<CommonResponse<Fruit>> createNewFruit(@RequestBody NewFruitRequest fruitRequest) {
        Fruit newFruit = fruitService.create(fruitRequest);
        CommonResponse<Fruit> response = CommonResponse.<Fruit>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("successfully create new fruit")
                .data(newFruit)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<Fruit>> getById(@PathVariable String id) {
        Fruit fruit = fruitService.getById(id);
        CommonResponse<Fruit> response = CommonResponse.<Fruit>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully fetch data")
                .data(fruit)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Fruit>>> getAllFruit(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "name", required = false) String name
    ) {


        SearchFruitRequest request = SearchFruitRequest.builder()
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .name(name)
                .build();
        Page<Fruit> fruitAll = fruitService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(fruitAll.getTotalPages())
                .totalElements(fruitAll.getTotalElements())
                .page(fruitAll.getPageable().getPageNumber() + 1)
                .size(fruitAll.getPageable().getPageSize())
                .hasNext(fruitAll.hasNext())
                .hasPrevious(fruitAll.hasPrevious())
                .build();

        CommonResponse<List<Fruit>> response = CommonResponse.<List<Fruit>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("success get all fruit")
                .data(fruitAll.getContent())
                .paging(pagingResponse)
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Fruit>> updateFruit(@RequestBody Fruit fruit) {
        Fruit update = fruitService.update(fruit);
        CommonResponse<Fruit> response = CommonResponse.<Fruit>builder()
                .statusCode(HttpStatus.OK.value())
                .message("successfully update data")
                .data(update)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = APIUrl.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<?>> deleteById(@PathVariable String id) {
        fruitService.deleteById(id);
        CommonResponse<Fruit> response = CommonResponse.<Fruit>builder()
                .statusCode(HttpStatus.OK.value())
                .message("OK, Success Delete Fruit with id " + id)
                .build();
        return ResponseEntity.ok(response);
    }

}
