package com.luky.fruit_sales.specification;

import com.luky.fruit_sales.dto.request.SearchFruitRequest;
import com.luky.fruit_sales.entity.Fruit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class FruitSpecification {
    public static Specification<Fruit> getSpecification(SearchFruitRequest searchFruitRequest){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates =new ArrayList<>();

            if (searchFruitRequest.getName() != null){
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("nama")), "%" + searchFruitRequest.getName().toLowerCase() + "%"));
            }
          return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
