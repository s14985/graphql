package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderProductResolver implements GraphQLResolver<OrderProduct> {
	private OrderServiceImpl orderService;
	private ProductServiceImpl productService;

	public Order getOrder(OrderProduct orderProduct) {
		return orderService.getProductById(orderProduct.getOrder().getId());
	}

	public Product getProduct(OrderProduct orderProduct) {
		return productService.getProductById(orderProduct.getProduct().getId());
	}
}
