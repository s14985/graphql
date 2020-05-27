package com.shop.graphql.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(exclude = {"orderProducts"})
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderProduct> orderProducts;

    public Product(Long id) {
        this.id = id;
    }

    public Product(String name) {
        this.name = name;
    }
}
