package com.shop.graphql.dto.input;

import lombok.Data;

@Data
public class NewProductOrderInput {
	private Long productId;
	private Integer quantity;
}
