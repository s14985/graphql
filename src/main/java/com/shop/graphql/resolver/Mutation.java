package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.EditProductOrderInput;
import com.shop.graphql.dto.input.NewProductOrderInput;
import com.shop.graphql.dto.input.OrderInput;
import com.shop.graphql.model.*;
import com.shop.graphql.service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
	private final UserService userService;
	private final OrderService orderService;
	private final ProductOrderService productOrderService;
	private final ProductService productService;
	private final DomainUserDetailsService userDetailsService;
	private final AuthenticationManager authenticationManager;

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
						productOrderService.create(
							new ProductOrder(
								finalOrder,
								productService.getProductById(
									newProductOrderInput.getProductId()
								),
								newProductOrderInput.getQuantity()
							)
						)
				)
				.collect(Collectors.toList())
		);

		return orderService.update(order);
	}

	public Order editOrder(
		Long id,
		List<EditProductOrderInput> editProductOrderInputs
	) {
		Order order = orderService.getOrderById(id);
		order.setProductOrders(
			editProductOrderInputs
				.stream()
				.map(
					newProductOrderInput -> {
						ProductOrder productOrder = productOrderService.getById(
							newProductOrderInput.getId()
						);
						productOrder.setOrder(order);
						productOrder.setProduct(
							productService.getProductById(newProductOrderInput.getProductId())
						);
						productOrder.setQuantity(newProductOrderInput.getQuantity());
						return productOrderService.create(productOrder);
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
		productOrderService.deleteAll(productOrderService.getAllByProductId(id));
		productService.delete(id);
		return true;
	}

	public User newUser(
		String firstName,
		String email,
		String password,
		Role role
	) {
		return userService.save(new User(firstName, email, password, role));
	}

	public User editUser(
		Long id,
		String firstName,
		String email,
		String password,
		List<OrderInput> orderInputs
	) {
		User user = userService.getUserById(id);
		user.setFirstName(firstName);
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

	public User login(String email, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
			userDetails,
			password,
			userDetails.getAuthorities()
		);
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder
				.getContext()
				.setAuthentication(usernamePasswordAuthenticationToken);
			return userService.getCurrentUser();
		} else {
			throw new BadCredentialsException(email);
		}
	}
}
