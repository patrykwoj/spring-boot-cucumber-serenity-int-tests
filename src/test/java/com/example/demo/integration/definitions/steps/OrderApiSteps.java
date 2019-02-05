package com.example.demo.integration.definitions.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.not;

import java.util.Arrays;

import com.example.demo.model.OrderRequest;
import com.example.demo.model.Product;

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
		SerenityRest.given().body(orderRequest).when().post("/order/add");
	}

	@Step
	public void orderHasOrderId() {
		// TODO Auto-generated method stub
		
	}

	@Step
	public void orderContainsAddedProduct() {
		// TODO Auto-generated method stub
		
	}

	@Step
	public void orderTotalPriceIsCorrect() {
		// TODO Auto-generated method stub
		
	}
	
	
}
