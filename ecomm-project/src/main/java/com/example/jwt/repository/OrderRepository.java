package com.example.jwt.repository;

import java.util.List;
import java.util.Optional;

import com.example.jwt.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

	@Query( value = "select * from orders o where o.customer_id = :sub" , nativeQuery = true )
	List<Orders> findAllByCustomerId(@Param("sub") String customerId);

	@Query("select o from Orders o where o.customerId = ?1 and o.productId = ?2")
	Optional<Orders> findByCustomerIdAndProductId(String customerId, Long productId);

}
