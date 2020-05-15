package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.exception.OrderNotFoundException;
import com.shop.graphql.exception.ProductNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.repository.OrderRepository;
import com.shop.graphql.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderProductResolver implements GraphQLResolver<OrderProduct> {
	private OrderRepository orderRepository;
	private ProductRepository productRepository;

	public Order getOrder(OrderProduct orderProduct) {
		return orderRepository
			.findById(orderProduct.getOrder().getId())
			.orElseThrow(
				() ->
					new OrderNotFoundException(
						"id",
						orderProduct.getOrder().getId().toString()
					)
			);
	}

	public Product getProduct(OrderProduct orderProduct) {
		return productRepository
			.findById(orderProduct.getProduct().getId())
			.orElseThrow(
				() ->
					new ProductNotFoundException(
						"id",
						orderProduct.getProduct().getId().toString()
					)
			);
	}
}
