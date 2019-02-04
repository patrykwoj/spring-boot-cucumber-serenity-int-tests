package com.example.demo.integration.definitions.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.model.Product;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class ProductApiSteps {

	Response httpResponse;

	Logger LOGGER = LoggerFactory.getLogger(ProductApiSteps.class);

	@Step
	public void login(String user) {
		LOGGER.info("User: " + user + " was succesfully logged in.");
	}

	@Step
	public void getProductsByStoreId(String store) {
		httpResponse = SerenityRest.given().config(SerenityRest.getDefaultConfig()).pathParam("store", store).when()
				.get("/products/store/{store}");
	}

	@Step
	public void getProductById(int productId) {
		httpResponse = SerenityRest.given().pathParam("productId", productId).when().get("/product/id/{productId}");
	}

	@Step
	public void responseHttpStatusEquals(int httpStatus) {
		assertThat(httpResponse.then().extract().statusCode(), is(equalTo(httpStatus)));
	}

	@Step
	public void responseContainsProductNamed(String productName) {
		Product[] products = httpResponse.body().as(Product[].class);
		assertThat(Arrays.asList(products), hasItem(hasProperty("name", equalTo(productName))));
	}

	@Step
	public void responseContainsNumberOfProducts(int numberOfProducts) {
		Product[] products = httpResponse.body().as(Product[].class);
		assertThat(Arrays.asList(products), hasSize(numberOfProducts));
	}

	@Step
	public void responseIsNotEmpty() {
		assertThat(httpResponse.body().asString(), not(isEmptyOrNullString()));
	}

	@Step
	public void responseIsEmpty() {
		assertThat(httpResponse.body().asString(), isEmptyOrNullString());
	}
}
