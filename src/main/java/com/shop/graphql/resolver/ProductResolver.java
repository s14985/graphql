package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.repository.OrderProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductResolver implements GraphQLResolver<Product> {
	private OrderProductRepository orderProductRepository;

	public Iterable<OrderProduct> getOrderProducts(Product product) {
		return orderProductRepository.findAllByProduct_Id(product.getId());
	}
}
