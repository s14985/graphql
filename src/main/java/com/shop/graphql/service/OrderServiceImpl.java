package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;

	@Override
	public @NotNull Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order create(
		@NotNull(message = "The order cannot be null.") @Valid Order order
	) {
		order.setDateCreated(OffsetDateTime.now());
		return orderRepository.save(order);
	}

	@Override
	public Order update(
		@NotNull(message = "The order cannot be null.") @Valid Order order
	) {
		return orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("order", "id", id.toString())
			);
	}

	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
