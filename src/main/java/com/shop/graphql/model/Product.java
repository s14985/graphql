package com.shop.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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

	private String manufacturer;

	private String itemCode;

	private String color;

	private String material;

	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
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
		String details,
		String manufacturer,
		String itemCode,
		String color,
		String material
	) {
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.details = details;
		this.manufacturer = manufacturer;
		this.itemCode = itemCode;
		this.color = color;
		this.material = material;
	}
}
