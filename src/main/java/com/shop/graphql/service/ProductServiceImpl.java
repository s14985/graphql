package com.shop.graphql.service;

import com.shop.graphql.exception.ProductNotFoundException;
import com.shop.graphql.model.Product;
import com.shop.graphql.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public @NotNull Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(@Min(value = 1L, message = "Invalid product ID.") Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("id", id.toString()));
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
