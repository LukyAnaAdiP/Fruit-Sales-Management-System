package com.luky.fruit_sales.specification;

import com.luky.fruit_sales.dto.request.SearchCustomerRequest;
import com.luky.fruit_sales.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                predicates.add(namePredicate);
            }

            if (request.getEmail() != null) {
                Predicate emailPredicate = criteriaBuilder.equal(root.get("email"), request.getEmail());
                predicates.add(emailPredicate);
            }

            if (request.getPhone() != null) {
                Predicate phonePredicate = criteriaBuilder.equal(root.get("phone"), request.getPhone());
                predicates.add(phonePredicate);
            }

            if (request.getAddress() != null) {
                Predicate addressPredicate = criteriaBuilder.equal(root.get("address"), request.getAddress());
                predicates.add(addressPredicate);
            }
            return query.where(criteriaBuilder.or(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
