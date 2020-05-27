package com.shop.graphql.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//	@NotNull(message = "First name is required.")
	//	@Basic(optional = false)
	//	private String firstName;
	//
	//	@NotNull(message = "Last name is required.")
	//	@Basic(optional = false)
	//	private String lastName;
	//
	//	@NotNull(message = "Street is required.")
	//	@Basic(optional = false)
	//	private String street;
	//
	//	@NotNull(message = "Zip code is required.")
	//	@Basic(optional = false)
	//	private String zipCode;
	//
	//	@NotNull(message = "City is required.")
	//	@Basic(optional = false)
	//	private String city;
	//
	//	@NotNull(message = "Country is required.")
	//	@Basic(optional = false)
	//	private String country;

	//    @NotNull(message = "Email is required.")
	//    @Basic(optional = false)
	private String email;

	//    @NotNull(message = "Password is required.")
	//    @Basic(optional = false)
	private String password;

	//    @NotNull(message = "User type is required.")
	//    @Basic(optional = false)
	private Role userType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Order> orders;

	public User(
		//		@NotNull(message = "First name is required.") String firstName,
		//		@NotNull(message = "Last name is required.") String lastName,
		//		@NotNull(message = "Street is required.") String street,
		//		@NotNull(message = "Zip code is required.") String zipCode,
		//		@NotNull(message = "City is required.") String city,
		//		@NotNull(message = "Country is required.") String country,
		@NotNull(message = "Email is required.") String email,
		@NotNull(message = "Password is required.") String password,
		@NotNull(message = "User type is required.") Role userType
	) {
		//		this.firstName = firstName;
		//		this.lastName = lastName;
		//		this.street = street;
		//		this.zipCode = zipCode;
		//		this.city = city;
		//		this.country = country;
		this.email = email;
		this.password = password;
		this.userType = userType;
	}
	//
	//	public Collection<? extends GrantedAuthority> getAuthorities() {
	//		return Collections.singletonList(() -> userType.getRole());
	//	}
}
