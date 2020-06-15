package com.shop.graphql;

import com.shop.graphql.model.*;
import com.shop.graphql.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(
		ProductRepository productRepository,
		OrderRepository orderRepository,
		ProductOrderRepository productOrderRepository,
		UserRepository userRepository
	) {
		return args -> {
			User user1 = new User("a.poziomka@gmail.com", "pass", Role.USER);
			User user2 = new User("b.poziomka@gmail.com", "pass", Role.USER);
			User user3 = new User("c.poziomka@gmail.com", "pass", Role.USER);

			Order order1 = new Order(Status.CREATED);
			Order order2 = new Order(Status.CREATED);
			Order order3 = new Order(Status.CREATED);
			Order order4 = new Order(Status.CREATED);
			Order order5 = new Order(Status.CREATED);
			Order order6 = new Order(Status.CREATED);

			Product product1 = new Product(
				"Koc 1",
				new BigDecimal("11.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);
			Product product2 = new Product(
				"Koc 2",
				new BigDecimal("12.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);
			Product product3 = new Product(
				"Koc 3",
				new BigDecimal("13.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);
			Product product4 = new Product(
				"Koc 4",
				new BigDecimal("14.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);
			Product product5 = new Product(
				"Koc 5",
				new BigDecimal("15.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);
			Product product6 = new Product(
				"Koc 6",
				new BigDecimal("16.00"),
				"https://loremflickr.com/320/240/boat",
				"details"
			);

			order1.setUser(user1);
			order2.setUser(user2);
			order3.setUser(user3);
			order4.setUser(user1);
			order5.setUser(user2);
			order6.setUser(user3);

			user1.setOrders(Arrays.asList(order1, order4));
			user2.setOrders(Arrays.asList(order2, order5));
			user3.setOrders(Arrays.asList(order3, order6));

			userRepository.saveAll(Arrays.asList(user1, user2, user3));

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
