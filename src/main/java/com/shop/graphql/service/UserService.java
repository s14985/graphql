package com.shop.graphql.service;

import com.shop.graphql.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface UserService {

    @NotNull Iterable<User> getAllUsers();

    User getUserById(@Min(value = 1L, message = "Invalid user ID.") Long id);

    User save(User user);

    User getCurrentUser();
}
