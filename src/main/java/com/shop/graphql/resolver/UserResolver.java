package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.Role;
import com.shop.graphql.model.User;
import com.shop.graphql.repository.OrderRepository;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver implements GraphQLResolver<User> {
	private OrderRepository orderRepository;

	public Iterable<Order> getOrders(User user) {
		return orderRepository.findAllById(
			user.getOrders().stream().map(Order::getId).collect(Collectors.toList())
		);
	}

	public Role getRole(User user) {
		return user.getUserType();
	}
}
