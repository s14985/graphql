package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.Product;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.service.OrderService;
import com.shop.graphql.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductOrderResolver implements GraphQLResolver<ProductOrder> {
	private OrderService orderService;
	private ProductService productService;

	public Order getOrder(ProductOrder productOrder) {
		return orderService.getOrderById(productOrder.getOrder().getId());
	}

	public Product getProduct(ProductOrder productOrder) {
		return productService.getProductById(productOrder.getProduct().getId());
	}
}
