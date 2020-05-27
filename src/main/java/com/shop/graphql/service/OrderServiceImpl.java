package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.User;
import com.shop.graphql.repository.OrderRepository;
import java.time.OffsetDateTime;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
	public List<Order> getUserOrders(User user) {
		return orderRepository.findAllByUser(user.getId());
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
