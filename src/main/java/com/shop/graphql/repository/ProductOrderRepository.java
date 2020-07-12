package com.shop.graphql.repository;

import com.shop.graphql.model.ProductOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOrderRepository
	extends CrudRepository<ProductOrder, Long> {

	@Query("SELECT po FROM ProductOrder po WHERE po.product.id = :id")
	List<ProductOrder> findAllByProduct_Id(Long id);

	@Query("SELECT po FROM ProductOrder po WHERE po.order.id = :id")
	List<ProductOrder> findAllByOrder_Id(Long id);

	@Query(
		"SELECT po FROM ProductOrder po " +
		"LEFT JOIN FETCH po.order o " +
		"LEFT JOIN FETCH po.product p " +
		"WHERE p.id = :id"
	)
	List<ProductOrder> getByProductIdWithAddData(Long id);
}
