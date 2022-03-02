package com.example.jwt.service;

import java.util.List;
import java.util.Optional;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Products;
import com.example.jwt.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Products> getAllProductService() {
		return productRepository.findAll();
	}

	@Transactional
	public Products saveProductService(Products product) {
		return productRepository.save(product);
	}

	@Transactional
	public Products updateProduct(Products products) throws ExceptionBroker {
		Optional<Products> opProducts = productRepository.findById(products.getProductId());
		if (!opProducts.isPresent()) {
			throw new ExceptionBroker("product not found", HttpStatus.NOT_FOUND);
		}

		Products productToUpdate = opProducts.get();
		productToUpdate.setExpiryDate(products.getExpiryDate());
		productToUpdate.setProductDescription(products.getProductDescription());
		productToUpdate.setProductName(products.getProductName());
		productToUpdate.setProductPrice(products.getProductPrice());
		productToUpdate.setProductWeight(products.getProductWeight());
		productToUpdate.setQuantity(products.getQuantity());

		productRepository.save(productToUpdate);
		return productToUpdate;

	}

}
