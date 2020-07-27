package com.shop.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shop.graphql.model.Address;
import com.shop.graphql.model.User;
import com.shop.graphql.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserResolver  implements GraphQLResolver<User> {
    private final AddressService addressService;

    public Address getAddress(User user) {
        return addressService.getAddressById(user.getId());
    }
}
