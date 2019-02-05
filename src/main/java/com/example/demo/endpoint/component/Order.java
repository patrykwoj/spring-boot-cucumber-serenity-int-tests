package com.example.demo.endpoint.component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.demo.model.Product;

import lombok.Data;

@Component
@SessionScope
@Data
public class Order {
	private Integer orderId = ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
	private Double totalPrice = 0.0;
	private Map<Product, Integer> products = new HashMap<>();

	public void incPrice(Double value) {
		totalPrice += value;
	}
}
