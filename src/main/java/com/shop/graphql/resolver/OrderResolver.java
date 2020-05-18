package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.User;
import com.shop.graphql.service.OrderProductServiceImpl;
import com.shop.graphql.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderResolver implements GraphQLResolver<Order> {
    private OrderProductServiceImpl orderProductService;
    private UserServiceImpl userService;

    public List<OrderProduct> getOrderProducts(Order order) {
        return orderProductService.getAllByOrder(order);
    }

    public User getUser(Order order) {
        return userService.getUserByOrderInOrders(order);
    }

    public OffsetDateTime getDateCreated(Order order) {
        return order.getDateCreated();
    }
}
