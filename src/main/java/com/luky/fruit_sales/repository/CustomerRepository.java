package com.luky.fruit_sales.repository;

import com.luky.fruit_sales.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // this is optional, but for consistency
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {
    @Modifying // must be added this anotation for modifyin data
    @Query(value = "Update m_customer SET address = :address WHERE id = :id", nativeQuery = true)
    void updateAddress(@Param("id") String id, @Param("address") String address);
}
