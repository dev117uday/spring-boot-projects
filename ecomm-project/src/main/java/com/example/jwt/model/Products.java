package com.example.jwt.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Products {
	
	@Id
	@GeneratedValue( strategy = GenerationType.SEQUENCE )
	private Long productId;
	@Size(max = 255)
	private String productName;
	@Size(max = 255)
	private String productDescription;
	
	@Positive
	private Float productPrice;
	
	@Positive
	private Float productWeight;
	
	private Date expiryDate;

	@Positive
	private Long quantity;

}
