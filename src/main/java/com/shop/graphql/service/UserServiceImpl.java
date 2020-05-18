package com.shop.graphql.service;

import com.shop.graphql.exception.UserNotFoundException;
import com.shop.graphql.model.User;
import com.shop.graphql.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    // TODO security
    private String getCurrentUserLogin() {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = "";
//        if (principal instanceof UserDetails) {
//            username = ((UserDetails) principal).getUsername();
//        } else {
//            username = principal.toString();
//        }
//        return username;
        return "a.poziomka@gmail.com";
    }

    @Override
    public @NotNull Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(@Min(value = 1L, message = "Invalid user ID.") Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("id", id.toString()));
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
                .orElseThrow(() -> new UserNotFoundException("email", login));
    }
}
