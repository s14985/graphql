package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OptionGroupResolver implements GraphQLResolver<OptionGroup> {
    private OptionRepository optionRepository;

    public Iterable<Option> getOptions(OptionGroup optionGroup) {
        return optionRepository.findAllById(
                optionGroup
                        .getOptions()
                        .stream()
                        .map(Option::getId)
                        .collect(Collectors.toList())
        );
    }
}
