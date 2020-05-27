package com.shop.graphql.service;

import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionRepository;
import java.util.Collections;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class OptionServiceImpl implements OptionService {
	private OptionRepository optionRepository;
	private OptionGroupServiceImpl optionGroupService;

	@Override
	public Iterable<Option> getOptionsByOptionGroup(OptionGroup optionGroup) {
		return optionRepository.findAllById(
			optionGroup
				.getOptions()
				.stream()
				.map(Option::getId)
				.collect(Collectors.toList())
		);
	}

	public Option save(String optionName, String optionGroupName) {
		Option option = new Option(optionName);
		OptionGroup optionGroup = optionGroupService.getOptionGroupByName(
			optionGroupName
		);
		if (optionGroup == null) {
			optionGroup = optionGroupService.save(optionGroupName, null);
			option.setOptionGroup(optionGroup);
			optionGroup.setOptions(Collections.singletonList(option));
		}
		option.setOptionGroup(optionGroup);
		return optionRepository.save(option);
	}

	public void delete(Long id) {
		optionRepository.deleteById(id);
	}
}
