package com.shop.graphql.dto.input;

import lombok.Data;

@Data
public class OrderProductInput {
	private Long id;
	private Long productId;
	private Integer quantity;
}
