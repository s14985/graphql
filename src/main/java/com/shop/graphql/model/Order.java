package com.shop.graphql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@ToString(exclude = { "productOrders" })
@EqualsAndHashCode(exclude = { "productOrders" })
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<ProductOrder> productOrders;

	public Order(Status status) {
		this.status = status;
		this.dateCreated = OffsetDateTime.now();
	}
}
