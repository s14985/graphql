package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.EditProductOrderInput;
import com.shop.graphql.dto.input.NewProductOrderInput;
import com.shop.graphql.dto.input.OrderInput;
import com.shop.graphql.model.*;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductOrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
	private UserServiceImpl userService;
	private OrderServiceImpl orderService;
	private ProductOrderServiceImpl orderProductService;
	private ProductServiceImpl productService;

	public Order newOrder(List<NewProductOrderInput> newProductOrderInputs) {
		Order order = new Order(Status.CREATED);
		order.setUser(userService.getCurrentUser());
		order = orderService.create(order);

		Order finalOrder = order;
		order.setProductOrders(
			newProductOrderInputs
				.stream()
				.map(
						newProductOrderInput ->
						orderProductService.create(
							new ProductOrder(
								finalOrder,
								productService.getProductById(newProductOrderInput.getProductId()),
								newProductOrderInput.getQuantity()
							)
						)
				)
				.collect(Collectors.toList())
		);

		return orderService.update(order);
	}

	public Order editOrder(Long id, List<EditProductOrderInput> editProductOrderInputs) {
		Order order = orderService.getOrderById(id);
		order.setProductOrders(
			editProductOrderInputs
				.stream()
				.map(
						newProductOrderInput -> {
						ProductOrder productOrder = orderProductService.getById(
							newProductOrderInput.getId()
						);
						productOrder.setOrder(order);
						productOrder.setProduct(
							productService.getProductById(newProductOrderInput.getProductId())
						);
						productOrder.setQuantity(newProductOrderInput.getQuantity());
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

	public Product editProduct(
		Long id,
		String name,
		BigDecimal price,
		String picture,
		String details
	) {
		Product product = productService.getProductById(id);
		product.setName(name);
		product.setPrice(price);
		product.setPicture(picture);
		product.setDetails(details);
		return productService.save(product);
	}

	public boolean deleteProduct(Long id) {
		orderProductService.deleteAll(orderProductService.getAllByProductId(id));
		productService.delete(id);
		return true;
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
