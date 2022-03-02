package com.example.jwt.repository;

import java.util.List;

import com.example.jwt.model.Orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	
	@Test
	public void getAllOrders() {
		List<Orders> orders = orderRepository.findAllByCustomerId("105230926503686689781");
		System.out.println(orders.toString());	
	}

}
