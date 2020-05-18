package com.shop.graphql.service;

import com.shop.graphql.model.Option;
import com.shop.graphql.model.OptionGroup;

public interface OptionService {
    Iterable<Option> getOptionsByOptionGroup(OptionGroup optionGroup);
}
