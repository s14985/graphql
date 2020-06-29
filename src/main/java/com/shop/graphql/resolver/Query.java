package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.Product;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.service.OrderService;
import com.shop.graphql.service.ProductOrderService;
import com.shop.graphql.service.ProductService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
	private final ProductService productService;
	private final OrderService orderService;
	private final ProductOrderService orderProductService;

	public Iterable<Product> findAllProducts() {
		return productService.getAllProducts();
	}

	public Iterable<Order> findAllOrders() {
		return orderService.getAllOrders();
	}

	public List<ProductOrder> findAllProductsFromOrdersByProductId(
		Long productId
	) {
		return orderProductService.getAllByProductId(productId);
	}

	public Product findProductById(Long id) {
		return productService.getProductById(id);
	}
}
