package com.shop.graphql;

import com.shop.graphql.model.Order;
import com.shop.graphql.model.Product;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.model.Status;
import com.shop.graphql.repository.OrderRepository;
import com.shop.graphql.repository.ProductOrderRepository;
import com.shop.graphql.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(
		ProductRepository productRepository,
		OrderRepository orderRepository,
		ProductOrderRepository productOrderRepository
	) {
		return args -> {
			Order order1 = new Order(Status.CREATED);
			Order order2 = new Order(Status.CREATED);
			Order order3 = new Order(Status.CREATED);
			Order order4 = new Order(Status.CREATED);
			Order order5 = new Order(Status.CREATED);
			Order order6 = new Order(Status.CREATED);

			String lorem =
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";
			String pic = "https://loremflickr.com/200/150/";

			Product product1 = new Product(
				"Item 1",
				new BigDecimal("11.00"),
				pic + "boat",
				lorem
			);
			Product product2 = new Product(
				"Item 2",
				new BigDecimal("12.00"),
				pic + "dog",
				lorem
			);
			Product product3 = new Product(
				"Item 3",
				new BigDecimal("13.00"),
				pic + "cat",
				lorem
			);
			Product product4 = new Product(
				"Item 4",
				new BigDecimal("14.00"),
				pic + "kite",
				lorem
			);
			Product product5 = new Product(
				"Item 5",
				new BigDecimal("15.00"),
				pic + "bee",
				lorem
			);
			Product product6 = new Product(
				"Item 6",
				new BigDecimal("16.00"),
				pic + "car",
				lorem
			);

			order1 = orderRepository.save(order1);
			order2 = orderRepository.save(order2);
			order3 = orderRepository.save(order3);
			order4 = orderRepository.save(order4);
			order5 = orderRepository.save(order5);
			order6 = orderRepository.save(order6);

			product1 = productRepository.save(product1);
			product2 = productRepository.save(product2);
			product3 = productRepository.save(product3);
			product4 = productRepository.save(product4);
			product5 = productRepository.save(product5);
			product6 = productRepository.save(product6);

			productOrderRepository.save(new ProductOrder(order1, product1, 5));
			productOrderRepository.save(new ProductOrder(order1, product2, 3));
			productOrderRepository.save(new ProductOrder(order1, product3, 1));

			productOrderRepository.save(new ProductOrder(order2, product1, 2));
			productOrderRepository.save(new ProductOrder(order2, product3, 4));

			productOrderRepository.save(new ProductOrder(order3, product1, 1));
			productOrderRepository.save(new ProductOrder(order3, product4, 4));
			productOrderRepository.save(new ProductOrder(order3, product3, 3));

			productOrderRepository.save(new ProductOrder(order4, product2, 2));

			productOrderRepository.save(new ProductOrder(order5, product2, 1));
			productOrderRepository.save(new ProductOrder(order5, product4, 2));

			productOrderRepository.save(new ProductOrder(order6, product2, 3));
		};
	}
}
