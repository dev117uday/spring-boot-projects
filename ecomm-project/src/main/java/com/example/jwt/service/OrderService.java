package com.example.jwt.service;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Orders;
import com.example.jwt.model.Products;
import com.example.jwt.repository.OrderRepository;
import com.example.jwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<Orders> getAllOrdersService(String customerId) {
		return orderRepository.findAllByCustomerId(customerId);
	}

	@Transactional
	public Orders saveProductToOrderService(Orders order) throws ExceptionBroker {

		Optional<Products> opProduct = productRepository.findById(order.getProductId());

		if (!opProduct.isPresent()) {
			throw new ExceptionBroker("product doesnt exists", HttpStatus.NOT_FOUND);
		}

		if (opProduct.get().getQuantity() < order.getQuantity()) {
			throw new ExceptionBroker("ordering to many products", HttpStatus.CONFLICT);
		}

		Products productToUpdate = opProduct.get();
		productToUpdate.setQuantity(productToUpdate.getQuantity() - order.getQuantity());

		Optional<Orders> orderToSave = orderRepository.findByCustomerIdAndProductId(order.getCustomerId(),
				productToUpdate.getProductId());

		if (!orderToSave.isPresent()) {
			return orderRepository.save(order);
		}

		productRepository.save(productToUpdate);
		orderToSave.get().setQuantity(orderToSave.get().getQuantity() + order.getQuantity());
		return orderRepository.save(orderToSave.get());

	}

	@Transactional
	public void deleteProductFromOrderService(Long productId) {
		orderRepository.deleteById(productId);
	}

}
