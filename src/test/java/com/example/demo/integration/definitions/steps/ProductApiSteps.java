package com.example.demo.integration.definitions.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;

import org.assertj.core.util.Arrays;

import com.example.demo.model.Product;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductApiSteps {

	@Step
	public void getProductsByStoreId(String store) {
		SerenityRest.given().pathParam("store", store).when().get("/products/store/{store}");
	}

	@Step
	public void getProductById(int productId) {
		SerenityRest.given().pathParam("productId", productId).when().get("/product/id/{productId}");
	}

	@Step
	public void responseContainsProductNamed(String productName) {
		Product[] products = SerenityRest.then().extract().body().as(Product[].class);
		assertThat(Arrays.asList(products), hasItem(hasProperty("name", equalTo(productName))));
	}

	@Step
	public void responseContainsNumberOfProducts(int numberOfProducts) {
		Product[] products = SerenityRest.then().extract().body().as(Product[].class);
		assertThat(Arrays.asList(products), hasSize(numberOfProducts));
	}
}
