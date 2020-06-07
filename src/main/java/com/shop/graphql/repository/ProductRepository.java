package com.shop.graphql.repository;

import com.shop.graphql.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query(
		"SELECT p FROM Product p " +
		"JOIN p.productOrders op " +
		"JOIN op.order o " +
		"WHERE o.id IN " +
		"    (SELECT o.id FROM Order o " +
		"    JOIN o.productOrders op " +
		"    JOIN op.product p " +
		"    WHERE p.id = :id " +
		"    ) " +
		"AND p NOT IN " +
		"	(SELECT p FROM Product p " +
		"	WHERE p.id = :id" +
		"	) " +
		"GROUP BY p.id"
	)
	Iterable<Product> findProductsFromOrdersByProductId(Long id);
}
