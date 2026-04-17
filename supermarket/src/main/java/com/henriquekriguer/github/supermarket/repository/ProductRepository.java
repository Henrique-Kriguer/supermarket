package com.henriquekriguer.github.supermarket.repository;

import com.henriquekriguer.github.supermarket.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
}
