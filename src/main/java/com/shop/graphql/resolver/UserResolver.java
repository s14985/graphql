package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.Role;
import com.shop.graphql.model.User;
import com.shop.graphql.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver implements GraphQLResolver<User> {
	private OrderService orderService;

	public Iterable<Order> getOrders(User user) {
		return orderService.getUserOrders(user);
	}

	public Role getRole(User user) {
		return user.getUserType();
	}
}
