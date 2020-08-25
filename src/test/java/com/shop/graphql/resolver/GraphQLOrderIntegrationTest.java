package com.shop.graphql.resolver;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class GraphQLOrderIntegrationTest {
	private static final String GRAPHQL_URL = "http://localhost:8081/graphql";

	@Test
	void getProductById_shouldReturnProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{\"query\": \"mutation($productOrders: [NewProductOrderInput!]!) { newOrder(productOrders: $productOrders) { id, status, dateCreated } }\", \"variables\": {\"productOrders\": [ { \"productId\": \"1\", \"quantity\": \"5\" }, { \"productId\": \"2\", \"quantity\": \"6\" } ] } }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.newOrder.id", is("50001"))
			.and()
			.body("data.newOrder.status", is("CREATED"));
	}
}
