package com.example.demo.integration;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.mapper.factory.Jackson2ObjectMapperFactory;
import net.serenitybdd.rest.SerenityRest;

@TestConfiguration
public class CucumberSerenityDemoTestConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(CucumberSerenityDemoTestConfiguration.class);

	@Value("${server.port}")
	private int port;

	@Value("${server.url}")
	private String baseUri;

	@Autowired
	private ObjectMapper objectMapper;

	@PostConstruct
	public void configureSerenityRest() {
		SerenityRest.setDefaultConfig(restAssuredConfig());
		SerenityRest.setDefaultPort(port);
		SerenityRest.setDefaultRequestSpecification(new RequestSpecBuilder().setBaseUri(baseUri)
				.setRelaxedHTTPSValidation().addHeaders(commonHeaders()).build());
		LOGGER.info("Serenity rest client instance was succesfully configured to work on: " + baseUri + ":" + port);
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
