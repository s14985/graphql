package com.shop.graphql.service;

import com.shop.graphql.model.Address;
import org.springframework.validation.annotation.Validated;

@Validated
public interface AddressService {
    Address getAddressById(Long id);
}
