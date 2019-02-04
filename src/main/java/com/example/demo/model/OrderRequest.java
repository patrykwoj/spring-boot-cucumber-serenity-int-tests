package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer productId;
	private Integer amount = 1;
}
