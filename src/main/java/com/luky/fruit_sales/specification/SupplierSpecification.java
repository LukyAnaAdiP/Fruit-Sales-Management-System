package com.luky.fruit_sales.specification;

import com.luky.fruit_sales.dto.request.SearchSupplierRequest;
import com.luky.fruit_sales.entity.Supplier;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SupplierSpecification {
    public static Specification<Supplier> getSpecification(SearchSupplierRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getContactInfo() != null) {
                Predicate contactInfoPredicate = criteriaBuilder.equal(root.get("contactInfo"), request.getContactInfo());
                predicates.add(contactInfoPredicate);
            }

            if (request.getAddress() != null) {
                Predicate addressPredicate = criteriaBuilder.equal(root.get("address"), request.getAddress());
                predicates.add(addressPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
