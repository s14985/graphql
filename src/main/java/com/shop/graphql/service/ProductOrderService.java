package com.shop.graphql.service;

import com.shop.graphql.model.ProductOrder;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductOrderService {
	ProductOrder create(
		@NotNull(
			message = "The products for order cannot be null."
		) @Valid ProductOrder productOrder
	);

	List<ProductOrder> getAllByProductId(Long id);

	List<ProductOrder> getAllByOrderId(Long id);

	List<ProductOrder> getByProductIdWithAddData(Long id);

	ProductOrder getById(Long id);

	void delete(Long id);

	Iterable<ProductOrder> getAllProductOrders();
}
