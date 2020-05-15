package com.shop.graphql.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(exclude = { "order", "product" })
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, updatable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false, updatable = false)
	private Product product;

	public OrderProduct(Long id) {
		this.id = id;
	}

	public OrderProduct(Order order, Product product) {
		this.order = order;
		this.product = product;
	}
}
