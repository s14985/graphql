package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.shop.graphql.dto.input.OrderProductInput;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Status;
import com.shop.graphql.service.OrderProductServiceImpl;
import com.shop.graphql.service.OrderServiceImpl;
import com.shop.graphql.service.ProductServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (OrderProductInput orderProductInput : orderProductInputs) {
            orderProducts.add(
                    orderProductService.create(
                            new OrderProduct(
                                    order,
                                    productService.getProductById(orderProductInput.getProduct().getId()),
                                    orderProductInput.getQuantity()
                            )
                    )
            );
        }

        order.setOrderProducts(orderProducts);
        return orderService.update(order);
    }

    public boolean deleteOrder(Long id) {
        orderService.delete(orderService.getProductById(id));
        return true;
    }
}
