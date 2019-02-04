package com.example.demo.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ProductException;
import com.example.demo.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	ObjectMapper objectMapper;

	ProductController(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@GetMapping(value = "/products/store/{store}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<Product>> getProducts(@PathVariable String store) throws ProductException, IOException {

		TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {
		};
		List<Product> response = new ArrayList<>();
		InputStream is = new ClassPathResource("products.json").getInputStream();
		List<Product> parsedJsonFile = objectMapper.readValue(is, mapType);

		response = parsedJsonFile.stream().filter(p -> p.getStore().equals(store)).collect(Collectors.toList());
		if (response.isEmpty())
			throw new ProductException("Wrong store id.");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/product/id/{productId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Product getProductById(@PathVariable Integer productId) throws ProductException, IOException {
		TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {
		};
		Product response = null;
		InputStream is = new ClassPathResource("products.json").getInputStream();
		List<Product> parsedJsonFile = objectMapper.readValue(is, mapType);

		response = parsedJsonFile.stream().filter(p -> p.getId().equals(productId)).findAny()
				.orElseThrow(() -> new ProductException("Product " + productId + " not found."));

		LOGGER.info("Product list loaded succesfully.");

		return response;
	}
}
