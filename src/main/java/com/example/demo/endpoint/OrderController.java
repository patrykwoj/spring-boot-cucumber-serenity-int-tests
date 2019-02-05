package com.example.demo.endpoint;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.endpoint.component.Order;
import com.example.demo.exception.OrderException;
import com.example.demo.exception.ProductException;
import com.example.demo.model.OrderRequest;
import com.example.demo.model.OrderResponse;
import com.example.demo.model.Product;

@RestController
public class OrderController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	private Order basket;
	private ProductController productController;

	OrderController(Order basket, ProductController productController) {
		this.basket = basket;
		this.productController = productController;
	}

	@PostMapping(value = "/order/add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<OrderResponse> processOrder(@RequestBody OrderRequest request)
			throws OrderException, ProductException, IOException {

		Product product = productController.getProductById(request.getProductId());

		if (basket.getProducts().containsKey(product)) {
			Integer amount = basket.getProducts().get(product);
			amount += request.getAmount();
			basket.getProducts().replace(product, amount);
		} else {
			basket.getProducts().put(product, request.getAmount());
		}

		basket.incPrice(request.getAmount() * product.getPrice());

		LOGGER.info("Product added to basket");

		OrderResponse response = new OrderResponse();
		response.setOrderId(basket.getOrderId());
		response.setTotalPrice(basket.getTotalPrice());
		for (Map.Entry<Product, Integer> entry : basket.getProducts().entrySet()) {
			response.getProductsAmount().put(entry.getKey().getName(), entry.getValue());
		}

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
