package com.shop.graphql.dto.input;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInput {
	private Long id;
	private String name;
	private BigDecimal price;
	private String picture;
	private String details;
}
