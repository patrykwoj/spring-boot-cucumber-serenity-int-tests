package com.example.demo.integration.definitions.steps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CommonSteps {

	Logger LOGGER = LoggerFactory.getLogger(CommonSteps.class);
	
	@Step
	public void login(String user) {
		LOGGER.info("User: " + user + " was succesfully logged in.");
	}
	
	@Step
	public void responseIsNotEmpty() {
		assertThat(SerenityRest.then().extract().body().asString(), not(isEmptyOrNullString()));
	}

	@Step
	public void responseIsEmpty() {
		assertThat(SerenityRest.then().extract().body().asString(), isEmptyOrNullString());
	}
	
	@Step
	public void responseHttpStatusEquals(int httpStatus) {
		assertThat(SerenityRest.then().extract().statusCode(), is(equalTo(httpStatus)));
		
	}
	
}
