package com.ac.curso.dto;

import java.io.Serializable;

import com.ac.curso.domain.Product;

public class ProductDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Double price;
	
	public ProductDTO() {}
	
	public ProductDTO(Product obj) {
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}