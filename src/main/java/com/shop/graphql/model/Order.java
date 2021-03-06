package com.shop.graphql.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Entity
@ToString(exclude = {"orderProducts", "user"})
@EqualsAndHashCode(exclude = {"orderProducts", "user"})
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
    private Set<OrderProduct> orderProducts;

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
        return this.orderProducts.size();
    }
}
