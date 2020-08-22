package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Address;
import com.shop.graphql.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
	private final AddressRepository addressRepository;

	@Override
	public Address getAddressById(Long id) {
		return addressRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("address", "id", id.toString())
			);
	}
}
