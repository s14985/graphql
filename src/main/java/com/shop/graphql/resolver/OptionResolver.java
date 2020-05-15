package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OptionResolver implements GraphQLResolver<Option> {
    private OptionGroupRepository optionGroupRepository;

    public Optional<OptionGroup> getOptionGroup(Option option) {
        return optionGroupRepository.findById(option.getOptionGroup().getId());
    }
}
