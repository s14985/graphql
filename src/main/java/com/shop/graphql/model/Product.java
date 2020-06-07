package com.shop.graphql.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(exclude = { "productOrders" })
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private BigDecimal price;

	private String picture;

	private String details;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<ProductOrder> productOrders;

	public Product(Long id) {
		this.id = id;
	}

	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public Product(
		String name,
		BigDecimal price,
		String picture,
		String details
	) {
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.details = details;
	}
}
