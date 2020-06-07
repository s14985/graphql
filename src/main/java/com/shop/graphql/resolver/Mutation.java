package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.OrderInput;
import com.shop.graphql.dto.input.ProductOrderInput;
import com.shop.graphql.model.*;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductOrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
	private UserServiceImpl userService;
	private OrderServiceImpl orderService;
	private ProductOrderServiceImpl orderProductService;
	private ProductServiceImpl productService;

	public Order newOrder(List<ProductOrderInput> productOrderInputs) {
		Order order = new Order(Status.CREATED);
		order.setUser(userService.getCurrentUser());
		order = orderService.create(order);

		Order finalOrder = order;
		order.setProductOrders(
			productOrderInputs
				.stream()
				.map(
					productOrderInput ->
						orderProductService.create(
							new ProductOrder(
								finalOrder,
								productService.getProductById(productOrderInput.getProductId()),
								productOrderInput.getQuantity()
							)
						)
				)
				.collect(Collectors.toList())
		);

		return orderService.update(order);
	}

	public Order editOrder(Long id, List<ProductOrderInput> productOrderInputs) {
		Order order = orderService.getOrderById(id);
		order.setProductOrders(
			productOrderInputs
				.stream()
				.map(
					productOrderInput -> {
						ProductOrder productOrder = orderProductService.getById(
							productOrderInput.getId()
						);
						productOrder.setOrder(order);
						productOrder.setProduct(
							productService.getProductById(productOrderInput.getProductId())
						);
						productOrder.setQuantity(productOrderInput.getQuantity());
						return orderProductService.create(productOrder);
					}
				)
				.collect(Collectors.toList())
		);
		return orderService.update(order);
	}

	public Product newProduct(
		String name,
		BigDecimal price,
		String picture,
		String details
	) {
		return productService.save(new Product(name, price, picture, details));
	}

	public Product editProduct(Long id, String name) {
		Product product = productService.getProductById(id);
		product.setName(name);
		return productService.save(product);
	}

	public User newUser(String email, String password, Role role) {
		return userService.save(new User(email, password, role));
	}

	public User editUser(
		Long id,
		String email,
		String password,
		List<OrderInput> orderInputs
	) {
		User user = userService.getUserById(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setOrders(
			orderInputs
				.stream()
				.map(orderInput -> orderService.getOrderById(orderInput.getId()))
				.collect(Collectors.toList())
		);
		return userService.save(user);
	}
}
