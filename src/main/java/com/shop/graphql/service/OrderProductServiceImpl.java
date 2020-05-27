package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.OrderProduct;
import com.shop.graphql.model.Product;
import com.shop.graphql.repository.OrderProductRepository;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OrderProductServiceImpl implements OrderProductService {
	private OrderProductRepository orderProductRepository;

	@Override
	public OrderProduct create(
		@NotNull(
			message = "The products for order cannot be null."
		) @Valid OrderProduct orderProduct
	) {
		return orderProductRepository.save(orderProduct);
	}

	@Override
	public List<OrderProduct> getAllByOrder(Order order) {
		return orderProductRepository.findAllByOrder_Id(order.getId());
	}

	@Override
	public List<OrderProduct> getByProductIdWithAddData(Long id) {
		return orderProductRepository.getByProductIdWithAddData(id);
	}

	@Override
	public List<OrderProduct> getAllByProduct(Product product) {
		return orderProductRepository.findAllByProduct_Id(product.getId());
	}

	@Override
	public OrderProduct getById(Long id) {
		return orderProductRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("orderProduct", "id", id.toString())
			);
	}

	@Override
	public void delete(Long id) {
		orderProductRepository.deleteById(id);
	}
}
