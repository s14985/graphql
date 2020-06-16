package com.shop.graphql.dto.input;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductInput {
	private Long id;
	private String name;
	private BigDecimal price;
	private String picture;
	private String details;
}
