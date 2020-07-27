package com.shop.graphql.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(exclude = { "users" })
@EqualsAndHashCode(exclude = { "users" })
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    private List<User> users;

    public Address(Long id, String street, String suite, String city, String zipcode) {
        this.id = id;
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
    }
}
