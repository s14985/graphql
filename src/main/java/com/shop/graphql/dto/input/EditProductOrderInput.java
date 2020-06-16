package com.shop.graphql.dto.input;

import lombok.Data;

@Data
public class EditProductOrderInput {
	private Long id;
	private Long orderId;
	private Long productId;
	private Integer quantity;
}
