package com.shop.graphql.service;

import com.shop.graphql.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ProductService {
	@NotNull
	Iterable<Product> getAllProducts();

	Product getProductById(
		@Min(value = 1L, message = "Invalid product ID.") Long id
	);

	Product save(Product product);

	void delete(Long id);

	Iterable<Product> findProductsFromOrdersByProductId(Long id);
}
