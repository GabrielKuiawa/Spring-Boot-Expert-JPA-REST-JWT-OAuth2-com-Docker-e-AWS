package com.github.GabrielKuiawa.productsapi.controller;

import com.github.GabrielKuiawa.productsapi.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class PoductController {

    @PostMapping
    public Product save(@RequestBody Product product) {
        System.out.println("Produto recebido " + product);
        return product;
    }
}
