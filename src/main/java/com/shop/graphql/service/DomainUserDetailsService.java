package com.shop.graphql.service;

import com.shop.graphql.model.User;
import com.shop.graphql.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class DomainUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String login) {
		String email = login.toLowerCase(Locale.ENGLISH);
		return userRepository
			.findByEmail(email)
			.map(this::createSpringSecurityUser)
			.orElseThrow(
				() ->
					new UsernameNotFoundException(
						"User with email" + email + " was not found in the database"
					)
			);
	}

	private org.springframework.security.core.userdetails.User createSpringSecurityUser(
		User user
	) {
		return new org.springframework.security.core.userdetails.User(
			user.getEmail(),
			user.getPassword(),
			user.getAuthorities()
		);
	}
}
