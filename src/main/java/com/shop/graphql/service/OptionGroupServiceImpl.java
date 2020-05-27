package com.shop.graphql.service;

import com.shop.graphql.exception.ResourceNotFoundException;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionGroupRepository;
import java.util.List;
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

	public OptionGroup save(String name, List<Option> options) {
		OptionGroup optionGroup = new OptionGroup(name);
		optionGroup.setOptions(options);
		return optionGroupRepository.save(optionGroup);
	}

	public void delete(Long id) {
		optionGroupRepository.deleteById(id);
	}

	public OptionGroup getOptionGroupById(Long id) {
		return optionGroupRepository
			.findById(id)
			.orElseThrow(
				() -> new ResourceNotFoundException("optionGroup", "id", id.toString())
			);
	}

	public OptionGroup getOptionGroupByName(String name) {
		return optionGroupRepository
			.findOneByName(name)
			.orElseThrow(
				() -> new ResourceNotFoundException("optionGroup", "name", name)
			);
	}
}
