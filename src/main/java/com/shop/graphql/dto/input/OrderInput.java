package com.shop.graphql.dto.input;

import com.shop.graphql.model.Status;
import lombok.Data;

@Data
public class OrderInput {
	private Long id;
	private Status status;
}
