package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.EditProductOrderInput;
import com.shop.graphql.dto.input.NewProductOrderInput;
import com.shop.graphql.model.*;
import com.shop.graphql.service.OrderService;
import com.shop.graphql.service.ProductOrderService;
import com.shop.graphql.service.ProductService;
import com.shop.graphql.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
	private final OrderService orderService;
	private final ProductOrderService productOrderService;
	private final ProductService productService;
	private final UserService userService;

	public Order newOrder(List<NewProductOrderInput> newProductOrderInputs) {
		User user = userService.getUserById(
			new RandomDataGenerator().nextLong(1L, 10000L)
		);
		Order order = Order.builder().status(Status.CREATED).user(user).build();
		order = orderService.create(order);

		Order finalOrder = order;
		order.setProductOrders(
			newProductOrderInputs
				.stream()
				.map(
					newProductOrderInput ->
						productOrderService.create(
							ProductOrder
								.builder()
								.order(finalOrder)
								.product(
									productService.getProductById(
										newProductOrderInput.getProductId()
									)
								)
								.quantity(newProductOrderInput.getQuantity())
								.build()
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

	public Order finishOrder(Long id) {
		Order order = orderService.getOrderById(id);
		order.setStatus(Status.FINISHED);
		return orderService.update(order);
	}

	public Product newProduct(
		String name,
		BigDecimal price,
		String picture,
		String details,
		String manufacturer,
		String itemCode,
		String color,
		String material
	) {
		return productService.save(
			new Product(
				name,
				price,
				picture,
				details,
				manufacturer,
				itemCode,
				color,
				material
			)
		);
	}

	public Product editProduct(
		Long id,
		String name,
		BigDecimal price,
		String picture,
		String details,
		String manufacturer,
		String itemCode,
		String color,
		String material
	) {
		Product product = productService.getProductById(id);
		product.setName(name);
		product.setPrice(price);
		product.setPicture(picture);
		product.setDetails(details);
		product.setManufacturer(manufacturer);
		product.setItemCode(itemCode);
		product.setColor(color);
		product.setMaterial(material);
		return productService.save(product);
	}

	public boolean deleteProduct(Long id) {
		productOrderService.deleteAll(productOrderService.getAllByProductId(id));
		productService.delete(id);
		return true;
	}
}
