package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Product;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.service.ProductOrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductResolver implements GraphQLResolver<Product> {
	private ProductOrderServiceImpl orderProductService;

	public Iterable<ProductOrder> getProductOrders(Product product) {
		return orderProductService.getAllByProductId(product.getId());
	}
}
