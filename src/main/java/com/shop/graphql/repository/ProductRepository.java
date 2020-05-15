package com.shop.graphql.repository;

import com.shop.graphql.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query(
		"SELECT p FROM Product p " +
		"JOIN p.orderProducts op " +
		"JOIN op.order o " +
		"WHERE o.id IN " +
		"    (SELECT o1.id FROM Order o1 " +
		"    JOIN o1.orderProducts op1 " +
		"    JOIN op1.product p1 " +
		"    WHERE p1.id = :id " +
		"    ) " +
		"GROUP BY p.id"
	)
	Iterable<Product> findProductsFromOrdersByProductId(Long id);
}
