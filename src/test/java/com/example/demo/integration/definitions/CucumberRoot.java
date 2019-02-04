package com.example.demo.integration.definitions;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import net.serenitybdd.rest.SerenityRest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberRoot {

	@LocalServerPort
	private int port;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void configureSerenityRest() {
		SerenityRest.setDefaultConfig(restAssuredConfig());
		SerenityRest.setDefaultPort(port);
		SerenityRest.setDefaultRequestSpecification(
				new RequestSpecBuilder().setRelaxedHTTPSValidation().addHeaders(commonHeaders()).build());
	}

	private RestAssuredConfig restAssuredConfig() {
		RestAssuredConfig config = SerenityRest.config();
		HttpClientConfig httpClientConfig = new HttpClientConfig();
		httpClientConfig.httpClientFactory(() -> HttpClientBuilder.create().useSystemProperties().build());
		config = config.set().httpClient(httpClientConfig);
		config = config.set().objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.JACKSON_2)
				.jackson2ObjectMapperFactory(new Jackson2ObjectMapperFactory() {
					@Override
					public ObjectMapper create(Type cls, String charset) {
						return objectMapper;
					}
				}));
		return config;
	}

	private Map<String, String> commonHeaders() {
		Map<String, String> commonHeadersMap = new HashMap<>();
		commonHeadersMap.put("License-Key", "stack_license");
		commonHeadersMap.put("Client-ID", "stackoverflow");
		return commonHeadersMap;
	}

}
