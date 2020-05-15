package com.shop.graphql.repository;

import com.shop.graphql.model.OrderProduct;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository
	extends CrudRepository<OrderProduct, Long> {
	List<OrderProduct> findAllByProduct_Id(Long id);

	Iterable<OrderProduct> findAllByOrder_Id(Long id);

	@Query(
		"SELECT op FROM OrderProduct op " +
		"LEFT JOIN FETCH op.order o " +
		"LEFT JOIN FETCH op.product p " +
		"WHERE p.id = :id"
	)
	List<OrderProduct> getByProductIdWithAddData(Long id);
}
