package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.service.OrderProductServiceImpl;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
	private ProductServiceImpl productService;
	private OrderServiceImpl orderService;
	private OrderProductServiceImpl orderProductService;

	public Iterable<Product> findAllProducts() {
		return productService.getAllProducts();
	}

	public Iterable<Order> findAllOrders() {
		return orderService.getAllOrders();
	}

	public Iterable<OrderProduct> findAllOrdersProducts() {
		return orderProductService.getAllOrderProducts();
	}

	public List<OrderProduct> findAllProductsFromOrdersByProductId(
		Long productId
	) {
		return orderProductService.getAllByProductId(productId);
	}

	public Iterable<Product> findProductsFromOrdersByProductId(Long productId) {
		return productService.findProductsFromOrdersByProductId(productId);
	}
}
