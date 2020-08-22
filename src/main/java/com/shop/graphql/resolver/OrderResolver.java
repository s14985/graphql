package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.model.User;
import com.shop.graphql.service.ProductOrderService;
import com.shop.graphql.service.UserService;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderResolver implements GraphQLResolver<Order> {
	private final ProductOrderService orderProductService;
	private final UserService userService;

	public List<ProductOrder> getProductOrders(Order order) {
		return orderProductService.getAllByOrderId(order.getId());
	}

	public OffsetDateTime getDateCreated(Order order) {
		return order.getDateCreated();
	}

	public User getUser(Order order) {
		return userService.getUserById(order.getUser().getId());
	}
}
