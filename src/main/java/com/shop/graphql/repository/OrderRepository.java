package com.shop.graphql.repository;

import com.shop.graphql.model.Order;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findAllByUser(Long id);
}
