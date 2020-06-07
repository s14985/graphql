package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.Product;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductOrderResolver implements GraphQLResolver<ProductOrder> {
	private OrderServiceImpl orderService;
	private ProductServiceImpl productService;

	public Order getOrder(ProductOrder productOrder) {
		return orderService.getOrderById(productOrder.getOrder().getId());
	}

	public Product getProduct(ProductOrder productOrder) {
		return productService.getProductById(productOrder.getProduct().getId());
	}
}
