package com.example.demo.integration.definitions.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasEntry;

import java.util.Arrays;

import com.example.demo.model.OrderRequest;
import com.example.demo.model.OrderResponse;
import com.example.demo.model.Product;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class OrderApiSteps {

	private Product selectedProduct;

	@Step
	public void getFirstProductFromProductApiResponse() {
		Product[] products = SerenityRest.then().extract().body().as(Product[].class);
		assertThat(products, is(not(emptyArray())));
		selectedProduct = Arrays.stream(products).findFirst().get();

	}

	@Step
	public void addSelectedProductToOrder(int pieces) {
		assertThat(selectedProduct, is(notNullValue()));
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setAmount(pieces);
		orderRequest.setProductId(selectedProduct.getId());
		Serenity.setSessionVariable("pieces").to(pieces);
		SerenityRest.given().body(orderRequest).when().post("/order/add");
	}

	@Step
	public void orderHasOrderId() {
		OrderResponse orderSummary = SerenityRest.then().extract().body().as(OrderResponse.class);
		assertThat(orderSummary.getOrderId(), is(notNullValue()));
	}

	@Step
	public void orderContainsAddedProduct() {
		OrderResponse orderSummary = SerenityRest.then().extract().body().as(OrderResponse.class);
		assertThat(orderSummary.getProductsAmount(), hasEntry(equalTo(selectedProduct.getName()), equalTo(Serenity.sessionVariableCalled("pieces"))));
	}

	@Step
	public void orderTotalPriceIsCorrect() {
		OrderResponse orderSummary = SerenityRest.then().extract().body().as(OrderResponse.class);
		double expectedTotalPrice = selectedProduct.getPrice() * (int)Serenity.sessionVariableCalled("pieces");
		assertThat(orderSummary.getTotalPrice(), is(equalTo(expectedTotalPrice)));
	}
	
	
}
