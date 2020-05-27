package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.service.OptionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OptionGroupResolver implements GraphQLResolver<OptionGroup> {
	private OptionServiceImpl optionService;

	public Iterable<Option> getOptions(OptionGroup optionGroup) {
		return optionService.getOptionsByOptionGroup(optionGroup);
	}
}
