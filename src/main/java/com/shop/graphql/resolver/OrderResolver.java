package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.model.User;
import com.shop.graphql.service.ProductOrderServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderResolver implements GraphQLResolver<Order> {
	private ProductOrderServiceImpl orderProductService;
	private UserServiceImpl userService;

	public List<ProductOrder> getProductOrders(Order order) {
		return orderProductService.getAllByOrderId(order.getId());
	}

	public User getUser(Order order) {
		return userService.getUserByOrderInOrders(order);
	}

	public OffsetDateTime getDateCreated(Order order) {
		return order.getDateCreated();
	}
}
