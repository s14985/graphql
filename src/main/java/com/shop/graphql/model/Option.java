package com.shop.graphql.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "optionGroup")
@Table(name = "option")
public class Option {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private OptionGroup optionGroup;

	public Option(String name) {
		this.name = name;
	}
}
