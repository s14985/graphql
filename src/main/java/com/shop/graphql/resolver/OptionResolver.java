package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.service.OptionGroupServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OptionResolver implements GraphQLResolver<Option> {
    private OptionGroupServiceImpl optionGroupService;

    public OptionGroup getOptionGroup(Option option) {
        return optionGroupService.getOptionGroupByOption(option);
    }
}
