package com.shop.graphql;

import com.shop.graphql.model.*;
import com.shop.graphql.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(
            ProductRepository productRepository,
            OrderRepository orderRepository,
            OrderProductRepository orderProductRepository,
            UserRepository userRepository,
            OptionRepository optionRepository,
            OptionGroupRepository optionGroupRepository
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

            Product product1 = new Product("Koc 1");
            Product product2 = new Product("Koc 2");
            Product product3 = new Product("Koc 3");
            Product product4 = new Product("Koc 4");
            Product product5 = new Product("Koc 5");
            Product product6 = new Product("Koc 6");

            order1.setUser(user1);
            order2.setUser(user2);
            order3.setUser(user3);
            order4.setUser(user1);
            order5.setUser(user2);
            order6.setUser(user3);

            Set<Order> userOrders1 = new HashSet<>();
            userOrders1.add(order1);
            userOrders1.add(order4);
            user1.setOrders(userOrders1);

            Set<Order> userOrders2 = new HashSet<>();
            userOrders2.add(order2);
            userOrders2.add(order5);
            user2.setOrders(userOrders2);

            Set<Order> userOrders3 = new HashSet<>();
            userOrders3.add(order3);
            userOrders3.add(order6);
            user3.setOrders(userOrders3);

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

            orderProductRepository.save(new OrderProduct(order1, product1));
            orderProductRepository.save(new OrderProduct(order1, product2));
            orderProductRepository.save(new OrderProduct(order1, product3));

            orderProductRepository.save(new OrderProduct(order2, product1));
            orderProductRepository.save(new OrderProduct(order2, product3));

            orderProductRepository.save(new OrderProduct(order3, product1));
            orderProductRepository.save(new OrderProduct(order3, product4));
            orderProductRepository.save(new OrderProduct(order3, product3));

            orderProductRepository.save(new OrderProduct(order4, product2));

            orderProductRepository.save(new OrderProduct(order5, product2));
            orderProductRepository.save(new OrderProduct(order5, product4));

            orderProductRepository.save(new OrderProduct(order6, product2));

            Option option1 = new Option("Option 1");
            Option option2 = new Option("Option 2");
            Option option3 = new Option("Option 3");
            Option option4 = new Option("Option 4");

            OptionGroup optionGroup1 = new OptionGroup("OptionGroup 1");
            OptionGroup optionGroup2 = new OptionGroup("OptionGroup 2");

            option1.setOptionGroup(optionGroup1);
            option2.setOptionGroup(optionGroup2);
            option3.setOptionGroup(optionGroup1);
            option4.setOptionGroup(optionGroup2);

            Set<Option> optionSet1 = new HashSet<>();
            optionSet1.add(option1);
            optionSet1.add(option3);
            optionGroup1.setOptions(optionSet1);

            Set<Option> optionSet2 = new HashSet<>();
            optionSet2.add(option2);
            optionSet2.add(option4);
            optionGroup2.setOptions(optionSet2);

            optionGroupRepository.saveAll(Arrays.asList(optionGroup1, optionGroup2));
            optionRepository.saveAll(
                    Arrays.asList(option1, option2, option3, option4)
            );
        };
    }
}
