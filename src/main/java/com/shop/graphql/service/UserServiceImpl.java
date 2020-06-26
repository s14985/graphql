package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Order;
import com.shop.graphql.model.User;
import com.shop.graphql.repository.UserRepository;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	private String getCurrentUserLogin() {
		Object principal = SecurityContextHolder
			.getContext()
			.getAuthentication()
			.getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	@Override
	public @NotNull Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(
		@Min(value = 1L, message = "Invalid user ID.") Long id
	) {
		return userRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("user", "id", id.toString())
			);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getCurrentUser() {
		String login = getCurrentUserLogin();
		return userRepository
			.findByEmail(login)
			.orElseThrow(() -> new ResourceNotFoundException("user", "email", login));
	}

	@Override
	public User getUserByOrderInOrders(Order order) {
		return userRepository
			.findOneByOrderInOrders(order.getId())
			.orElseThrow(
				() ->
					new ResourceNotFoundException(
						"user",
						"orderId",
						order.getId().toString()
					)
			);
	}
}
