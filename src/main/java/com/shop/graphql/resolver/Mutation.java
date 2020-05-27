package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.OrderInput;
import com.shop.graphql.dto.input.OrderProductInput;
import com.shop.graphql.model.*;
import com.shop.graphql.service.OrderProductServiceImpl;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class Mutation implements GraphQLMutationResolver {
    private UserServiceImpl userService;
    private OrderServiceImpl orderService;
    private OrderProductServiceImpl orderProductService;
    private ProductServiceImpl productService;

    public Order newOrder(List<OrderProductInput> orderProductInputs) {
        Order order = new Order(Status.CREATED);
        order.setUser(userService.getCurrentUser());
        order = orderService.create(order);

        Order finalOrder = order;
        order.setOrderProducts(
                orderProductInputs
                        .stream()
                        .map(
                                orderProductInput ->
                                        orderProductService.create(
                                                new OrderProduct(
                                                        finalOrder,
                                                        productService.getProductById(orderProductInput.getProductId()),
                                                        orderProductInput.getQuantity()
                                                )
                                        )
                        )
                        .collect(Collectors.toList())
        );

        return orderService.update(order);
    }

    public Order editOrder(Long id, List<OrderProductInput> orderProductInputs) {
        Order order = orderService.getOrderById(id);
        order.setOrderProducts(
                orderProductInputs
                        .stream()
                        .map(
                                orderProductInput -> {
                                    OrderProduct orderProduct = orderProductService.getById(
                                            orderProductInput.getId()
                                    );
                                    orderProduct.setOrder(order);
                                    orderProduct.setProduct(
                                            productService.getProductById(orderProductInput.getProductId())
                                    );
                                    orderProduct.setQuantity(orderProductInput.getQuantity());
                                    return orderProductService.create(orderProduct);
                                }
                        )
                        .collect(Collectors.toList())
        );
        return orderService.update(order);
    }

    public Product newProduct(String name) {
        return productService.save(new Product(name));
    }

    public Product editProduct(Long id, String name) {
        Product product = productService.getProductById(id);
        product.setName(name);
        return productService.save(product);
    }

    public User newUser(String email, String password, Role role) {
        return userService.save(new User(email, password, role));
    }

    public User editUser(
            Long id,
            String email,
            String password,
            List<OrderInput> orderInputs
    ) {
        User user = userService.getUserById(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setOrders(
                orderInputs
                        .stream()
                        .map(orderInput -> orderService.getOrderById(orderInput.getId()))
                        .collect(Collectors.toList())
        );
        return userService.save(user);
    }
}
