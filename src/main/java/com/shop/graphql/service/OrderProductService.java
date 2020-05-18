package com.shop.graphql.service;

import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface OrderProductService {
    OrderProduct create(
            @NotNull(
                    message = "The products for order cannot be null."
            ) @Valid OrderProduct orderProduct
    );

    List<OrderProduct> getAllByProduct(Product product);

    List<OrderProduct> getAllByOrder(Order order);

    List<OrderProduct> getByProductIdWithAddData(Long id);

    OrderProduct getById(Long id);
}
