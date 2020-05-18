package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.exception.UserNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.User;
import com.shop.graphql.repository.OrderProductRepository;
import com.shop.graphql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class OrderResolver implements GraphQLResolver<Order> {
    private OrderProductRepository orderProductRepository;
    private UserRepository userRepository;

    public List<OrderProduct> getOrderProducts(Order order) {
        return orderProductRepository.findAllByOrder_Id(order.getId());
    }

    public User getUser(Order order) {
        return userRepository
                .findOneByOrderInOrders(order.getId())
                .orElseThrow(
                        () -> new UserNotFoundException("orderId", order.getId().toString())
                );
    }

    public OffsetDateTime getDateCreated(Order order) {
        return order.getDateCreated();
    }
}
