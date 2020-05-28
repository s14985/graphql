package com.shop.graphql.service;

import com.shop.graphql.model.OrderProduct;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderProductService {
	OrderProduct create(
		@NotNull(
			message = "The products for order cannot be null."
		) @Valid OrderProduct orderProduct
	);

	List<OrderProduct> getAllByProductId(Long id);

	List<OrderProduct> getAllByOrderId(Long id);

	List<OrderProduct> getByProductIdWithAddData(Long id);

	OrderProduct getById(Long id);

	void delete(Long id);

	Iterable<OrderProduct> getAllOrderProducts();
}
