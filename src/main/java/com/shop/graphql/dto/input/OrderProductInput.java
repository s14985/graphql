package com.shop.graphql.dto.input;

import lombok.Data;

@Data
public class OrderProductInput {
    private ProductInput product;
    private Integer quantity;
}
