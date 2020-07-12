package com.shop.graphql;

import com.shop.graphql.model.ProductOrder;
import com.shop.graphql.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
    private final ProductOrderService productOrderService;

    @GetMapping("/{id}")
    public ResponseEntity<Iterable<ProductOrder>> get(@PathVariable Long id) {
        return new ResponseEntity<>(productOrderService.getAllByProductId(id), HttpStatus.OK);
    }
}
