package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.repository.OrderProductRepository;
import com.shop.graphql.repository.OrderRepository;
import com.shop.graphql.repository.ProductRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Query implements GraphQLQueryResolver {
	private ProductRepository productRepository;
	private OrderRepository orderRepository;
	private OrderProductRepository orderProductRepository;

	public Iterable<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Iterable<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	public Iterable<OrderProduct> findAllOrdersProducts() {
		return orderProductRepository.findAll();
	}

	public List<OrderProduct> findAllProductsFromOrdersByProductId(
		Long productId
	) {
		return orderProductRepository.findAllByProduct_Id(productId);
	}

	public Iterable<Product> findProductsFromOrdersByProductId(Long productId) {
		return productRepository.findProductsFromOrdersByProductId(productId);
	}
}
