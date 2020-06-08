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
public class ProductOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(nullable = false)
	private Integer quantity;

	public ProductOrder(Long id) {
		this.id = id;
	}

	public ProductOrder(Order order, Product product, Integer quantity) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}
}