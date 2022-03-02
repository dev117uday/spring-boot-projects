package com.example.jwt.controller;

import java.util.List;

import com.example.jwt.exception.ExceptionBroker;
import com.example.jwt.model.Products;
import com.example.jwt.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/s")
	public Products getSomething() {
		return new Products();
	}

	@GetMapping
	public ResponseEntity<List<Products>> getAllProduct() {
		List<Products> products = productService.getAllProductService();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}

	@PostMapping
	public ResponseEntity<Products> saveProduct(@RequestBody Products products) {
		Products product = productService.saveProductService(products);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@PatchMapping
	public ResponseEntity<Products> updateProduct(@RequestBody Products products) throws ExceptionBroker {
		Products updatedProduct = productService.updateProduct(products);
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}

}
