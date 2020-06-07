package com.shop.graphql.repository;

import com.shop.graphql.model.ProductOrder;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository
	extends CrudRepository<ProductOrder, Long> {
	List<ProductOrder> findAllByProduct_Id(Long id);

	List<ProductOrder> findAllByOrder_Id(Long id);

	@Query(
		"SELECT op FROM ProductOrder op " +
		"LEFT JOIN FETCH op.order o " +
		"LEFT JOIN FETCH op.product p " +
		"WHERE p.id = :id"
	)
	List<ProductOrder> getByProductIdWithAddData(Long id);
}
