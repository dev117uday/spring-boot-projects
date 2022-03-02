package com.example.jwt.controller;

import java.util.List;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Orders;
import com.example.jwt.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public List<Orders> getAllOrders() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userID = userDetails.getUsername();

		return orderService.getAllOrdersService(userID);
	}

	@PostMapping
	public ResponseEntity<Orders> saveOrderController(@RequestBody Orders order) throws ExceptionBroker {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userID = userDetails.getUsername();

		order.setCustomerId(userID);
		Orders orderSaved = orderService.saveProductToOrderService(order);

		return ResponseEntity.status(HttpStatus.OK).body(orderSaved);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteOrder( @PathVariable("id") Long productId ) {

		orderService.deleteProductFromOrderService(productId);		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
