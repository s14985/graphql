package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.service.OrderProductServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductResolver implements GraphQLResolver<Product> {
	private OrderProductServiceImpl orderProductService;

	public Iterable<OrderProduct> getOrderProducts(Product product) {
		return orderProductService.getAllByProduct(product);
	}
}
