package com.shop.graphql.repository;

import com.shop.graphql.model.OptionGroup;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionGroupRepository
	extends CrudRepository<OptionGroup, Long> {
	Optional<OptionGroup> findOneByName(String name);
}
