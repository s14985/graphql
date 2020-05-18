package com.shop.graphql.service;

import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;
import com.shop.graphql.repository.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class OptionServiceImpl implements OptionService {
    private OptionRepository optionRepository;

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
}
