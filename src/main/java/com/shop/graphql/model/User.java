package com.shop.graphql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@ToString(exclude = { "address", "orders" })
@EqualsAndHashCode(exclude = { "address", "orders" })
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String firstName;
	private String lastName;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Order> orders;
}
