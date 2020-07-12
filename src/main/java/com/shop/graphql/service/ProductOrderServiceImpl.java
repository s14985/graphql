package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.repository.ProductOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
	private ProductOrderRepository productOrderRepository;

	@Override
	public ProductOrder create(
		@NotNull(
			message = "The products for order cannot be null."
		) @Valid ProductOrder productOrder
	) {
		return productOrderRepository.save(productOrder);
	}

	public List<ProductOrder> getAllByOrderId(Long id) {
		return productOrderRepository.findAllByOrder_Id(id);
	}

	@Override
	public List<ProductOrder> getByProductIdWithAddData(Long id) {
		return productOrderRepository.getByProductIdWithAddData(id);
	}

	@Override
	public List<ProductOrder> getAllByProductId(Long id) {
		return productOrderRepository.findAllByProduct_Id(id);
	}

	@Override
	public ProductOrder getById(Long id) {
		return productOrderRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("productOrder", "id", id.toString())
			);
	}

	@Override
	public void delete(Long id) {
		productOrderRepository.deleteById(id);
	}

	@Override
	public Iterable<ProductOrder> getAllProductOrders() {
		return productOrderRepository.findAll();
	}

	public void deleteAll(List<ProductOrder> productOrders) {
		productOrderRepository.deleteAll(productOrders);
	}
}
