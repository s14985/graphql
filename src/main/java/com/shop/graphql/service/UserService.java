package com.shop.graphql.service;

import com.shop.graphql.model.User;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
	User getUserById(Long id);
}
