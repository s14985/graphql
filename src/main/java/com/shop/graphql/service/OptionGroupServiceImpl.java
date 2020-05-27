package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OptionGroupServiceImpl implements OptionGroupService {
	private OptionGroupRepository optionGroupRepository;

	public OptionGroup getOptionGroupByOption(Option option) {
		return optionGroupRepository
			.findById(option.getOptionGroup().getId())
			.orElseThrow(
				() ->
					new ResourceNotFoundException(
						"optionGroup",
						"id",
						option.getOptionGroup().getId().toString()
					)
			);
	}
}
