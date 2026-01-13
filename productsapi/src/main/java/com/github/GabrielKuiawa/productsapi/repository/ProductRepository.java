package com.github.GabrielKuiawa.productsapi.repository;

import com.github.GabrielKuiawa.productsapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
