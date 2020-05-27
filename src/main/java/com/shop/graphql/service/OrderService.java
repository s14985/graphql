package com.shop.graphql.service;

import com.shop.graphql.model.Order;
import com.shop.graphql.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderService {
    @NotNull
    Iterable<Order> getAllOrders();

    Order create(
            @NotNull(message = "The order cannot be null.") @Valid Order order
    );

    Order update(
            @NotNull(message = "The order cannot be null.") @Valid Order order
    );

    List<Order> getUserOrders(User user);

    Order getProductById(@Min(value = 1L, message = "Invalid order ID.") Long id);

    void delete(@Valid Order order);
}
