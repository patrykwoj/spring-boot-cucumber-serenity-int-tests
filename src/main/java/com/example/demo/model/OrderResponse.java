package com.example.demo.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class OrderResponse {
	private Integer orderId;
	private Double totalPrice = 0.0;
	private Map<String, Integer> productsAmount = new HashMap<>();
}
