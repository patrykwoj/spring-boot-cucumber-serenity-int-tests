package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Double price = 0.0;
	private String store;
	
}
