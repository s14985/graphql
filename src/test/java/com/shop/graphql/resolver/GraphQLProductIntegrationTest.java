package com.shop.graphql.resolver;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GraphQLProductIntegrationTest {
	private static final String GRAPHQL_URL = "http://localhost:8081/graphql";

	@Test
	void getProductById_shouldReturnProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findProductById(id: 1) { id, name, price, picture, details, manufacturer, itemCode, color, material } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findProductById.id", is("1"))
			.and()
			.body("data.findProductById.name", is("product 1"));
	}

	@Test
	void getProductWithProductOrdersById_shouldReturnProductWithProductOrders() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findProductById(id: 1) { id, name, price, picture, details, manufacturer, itemCode, color, material, productOrders { id, quantity } } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findProductById.id", is("1"))
			.and()
			.body("data.findProductById.name", is("product 1"))
			.and()
			.body("data.findProductById.productOrders.size()", is(4))
			.and()
			.body("data.findProductById.productOrders[0].id", is("14947"));
	}

	@Test
	void getProductWithProductOrdersWithOrderById_shouldReturnProductWithProductOrdersWithOrder() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findProductById(id: 1) { id, name, price, picture, details, manufacturer, itemCode, color, material, productOrders { id, order { id, dateCreated, status }, quantity } } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findProductById.id", is("1"))
			.and()
			.body("data.findProductById.name", is("product 1"))
			.and()
			.body("data.findProductById.productOrders[0].id", is("14947"))
			.and()
			.body(
				"data.findProductById.productOrders[0].order.status",
				is("CREATED")
			);
	}

	@Test
	void getProductWithProductOrdersWithOrderWithUserById_shouldReturnProductWithProductOrdersWithOrderWithUser() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findProductById(id: 1) { id, name, price, picture, details, manufacturer, itemCode, color, material, productOrders { id, order { id, dateCreated, status, user { id, username, firstName, lastName } }, quantity } } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findProductById.id", is("1"))
			.and()
			.body("data.findProductById.name", is("product 1"))
			.and()
			.body("data.findProductById.productOrders[0].id", is("14947"))
			.and()
			.body("data.findProductById.productOrders[0].order.status", is("CREATED"))
			.and()
			.body(
				"data.findProductById.productOrders[0].order.user.username",
				is("user_7888")
			);
	}

	@Test
	void getProductWithProductOrdersWithOrderWithUserWithAddressById_shouldReturnProductWithProductOrdersWithOrderWithUserWithAddress() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findProductById(id: 1) { id, name, price, picture, details, manufacturer, itemCode, color, material, productOrders { id, order { id, dateCreated, status, user { id, username, firstName, lastName, address { id, street, suite, city, zipcode } } }, quantity } } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findProductById.id", is("1"))
			.and()
			.body("data.findProductById.name", is("product 1"))
			.and()
			.body("data.findProductById.productOrders[0].id", is("14947"))
			.and()
			.body("data.findProductById.productOrders[0].order.status", is("CREATED"))
			.and()
			.body(
				"data.findProductById.productOrders[0].order.user.username",
				is("user_7888")
			)
			.and()
			.body(
				"data.findProductById.productOrders[0].order.user.address.street",
				is("street 7888")
			);
	}

	@Test
	void getSuggestedProductsByProductId_shouldReturnProducts() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"{ findAllProductsFromOrdersByProductId(id: 1) { order { productOrders { product { id, name, price, picture, details, manufacturer, itemCode, color, material } } } } }\" }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.findAllProductsFromOrdersByProductId.size()", is(4))
			.and()
			.body(
				"data.findAllProductsFromOrdersByProductId[0].order.productOrders.size()",
				is(4)
			)
			.and()
			.body(
				"data.findAllProductsFromOrdersByProductId[0].order.productOrders[1].product.id",
				is("17038")
			);
	}

	@Test
	void addProduct_shouldReturnNewProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"mutation($name: String!, $price: Float!, $picture: String!, $details: String!, $manufacturer: String!, $itemCode: String!, $color: String!, $material: String!) { newProduct(name: $name, price: $price, picture: $picture, details: $details, manufacturer: $manufacturer, itemCode: $itemCode, color: $color, material: $material) { id, name, price, picture, details, manufacturer, itemCode, color, material } }\",\"variables\": {\"name\": \"new product\", \"price\": 10, \"picture\": \"picture\", \"details\": \"details\", \"manufacturer\": \"manufacturer\", \"itemCode\": \"itemCode\", \"color\": \"color\", \"material\": \"material\" } }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.newProduct.name", is("new product"));
	}

	@Test
	void editProduct_shouldReturnEditedProduct() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"mutation($id: ID!, $name: String!, $price: Float!, $picture: String!, $details: String!, $manufacturer: String!, $itemCode: String!, $color: String!, $material: String!) { editProduct(id: $id, name: $name, price: $price, picture: $picture, details: $details, manufacturer: $manufacturer, itemCode: $itemCode, color: $color, material: $material) { id, name, price, picture, details, manufacturer, itemCode, color, material } }\",\"variables\": {\"id\": \"100001\", \"name\": \"edited product\", \"price\": 10, \"picture\": \"picture\", \"details\": \"details\", \"manufacturer\": \"manufacturer\", \"itemCode\": \"itemCode\", \"color\": \"color\", \"material\": \"material\" } }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.editProduct.name", is("edited product"));
	}

	@Test
	void deleteProduct_shouldReturnTrue() {
		given()
			.contentType(ContentType.JSON)
			.body(
				"{ \"query\": \"mutation($id: ID!) { deleteProduct(id: $id)  }\",\"variables\": {\"id\": \"100001\"} }"
			)
			.post(GRAPHQL_URL)
			.then()
			.statusCode(200)
			.and()
			.body("data.deleteProduct", is(true));
	}
}
