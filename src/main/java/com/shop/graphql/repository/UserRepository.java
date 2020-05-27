package com.shop.graphql.repository;

import com.shop.graphql.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	@Query("select u from User u join u.orders o where o.id = :id")
	Optional<User> findOneByOrderInOrders(Long id);

	Optional<User> findByEmail(String email);
}
