package com.shop.graphql.dto.input;

import lombok.Data;

@Data
public class ProductOrderInput {
	private Long id;
	private Long orderId;
	private Long productId;
	private Integer quantity;
}
