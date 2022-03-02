package com.example.jwt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Orders {

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	@Column( columnDefinition = "serial" )
	private Long OrderId;

	private String customerId;
	private Long productId;
	private String shippingAddress;
	private Date orderDate;
	private String orderStatus;
	private Long quantity;
}
