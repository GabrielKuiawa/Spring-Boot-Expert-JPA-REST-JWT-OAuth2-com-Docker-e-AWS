package com.github.GabrielKuiawa.productsapi.controller;

import com.github.GabrielKuiawa.productsapi.model.Product;
import com.github.GabrielKuiawa.productsapi.repository.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class PoductController {

    private ProductRepository productRepository;

    public PoductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        var id = UUID.randomUUID().toString();
        product.setId(id);
        productRepository.save(product);
        System.out.println("Produto recebido " + product);
        return product;
    }
}
