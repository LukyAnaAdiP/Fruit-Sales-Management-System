package com.luky.fruit_sales.service.impl;

import com.luky.fruit_sales.dto.request.NewFruitRequest;
import com.luky.fruit_sales.dto.request.SearchFruitRequest;
import com.luky.fruit_sales.entity.Fruit;
import com.luky.fruit_sales.repository.FruitRepository;
import com.luky.fruit_sales.service.FruitService;
import com.luky.fruit_sales.specification.FruitSpecification;
import com.luky.fruit_sales.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    private final ValidationUtil validationUtil;

    @Override
    public Fruit create(NewFruitRequest fruitRequest) {
        validationUtil.validate(fruitRequest);

        Fruit newFruit = Fruit.builder()
                .name(fruitRequest.getName())
                .price(fruitRequest.getPrice())
                .stock(fruitRequest.getStock())
                .build();

        return fruitRepository.saveAndFlush(newFruit);
    }

    @Override
    public Fruit getById(String id) {
        Optional<Fruit> optionalFruit = fruitRepository.findById(id);
        if (optionalFruit.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "fruit not found");
        }
        return optionalFruit.get();
    }

    @Override
    public Page<Fruit> getAll(SearchFruitRequest fruitRequest) {
        if (fruitRequest.getPage() <= 0) {
            fruitRequest.setPage(1);
        }

        String validSortBy;
        if ("name".equalsIgnoreCase(fruitRequest.getSortBy()) || "price".equalsIgnoreCase(fruitRequest.getSortBy()) || "stock".equalsIgnoreCase(fruitRequest.getSortBy())) {
            validSortBy = fruitRequest.getSortBy();
        } else {
            validSortBy = "name";
        }

        Sort sort = Sort.by(Sort.Direction.fromString(fruitRequest.getDirection()), validSortBy);
        Pageable pageable = PageRequest.of((fruitRequest.getPage() - 1), fruitRequest.getSize(), sort);

        Specification<Fruit> specification = FruitSpecification.getSpecification(fruitRequest);
        return fruitRepository.findAll(specification, pageable);
    }

    @Override
    public Fruit update(Fruit fruit) {
        getById(fruit.getId());
        return fruitRepository.saveAndFlush(fruit);
    }

    @Override
    public void deleteById(String id) {
        Fruit currentFruit = getById(id);
        fruitRepository.delete(currentFruit);
    }
}
