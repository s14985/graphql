package com.shop.graphql.service;

import com.shop.graphql.model.Order;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface OrderService {
	@NotNull
	Iterable<Order> getAllOrders();

	Order create(
		@NotNull(message = "The order cannot be null.") @Valid Order order
	);

	Order update(
		@NotNull(message = "The order cannot be null.") @Valid Order order
	);

	Order getOrderById(@Min(value = 1L, message = "Invalid order ID.") Long id);

	void delete(Long id);
}
