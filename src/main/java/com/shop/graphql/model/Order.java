package com.shop.graphql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import lombok.*;

@Data
@Entity
@ToString(exclude = { "productOrders", "user" })
@EqualsAndHashCode(exclude = { "productOrders", "user" })
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private OffsetDateTime dateCreated;

	private Status status;

	@JsonIgnore
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private User user;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	@Valid
	private List<ProductOrder> productOrders;

	public Order(Status status) {
		this.status = status;
		this.dateCreated = OffsetDateTime.now();
	}

	//	@Transient
	//	public Double getTotalOrderPrice() {
	//		List<OrderProduct> orderProducts = getOrderProducts();
	//		return orderProducts.stream().map(OrderProduct::getTotalPrice).reduce(Double::sum).get();
	//	}

	@Transient
	public int getNumberOfProducts() {
		return this.productOrders.size();
	}
}
