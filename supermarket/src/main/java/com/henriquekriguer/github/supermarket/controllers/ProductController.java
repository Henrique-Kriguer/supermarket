package com.henriquekriguer.github.supermarket.controllers;

import com.henriquekriguer.github.supermarket.model.Product;
import com.henriquekriguer.github.supermarket.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServices service;

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> findAll(){
        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Product findById(@PathVariable ("id") Long id){

        return service.findById(id);
    }
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product create(@RequestBody Product product){

        return service.create(product);
    }
    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Product update(@RequestBody Product product) {

        return service.update(product);
    }
    @DeleteMapping( value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }



}
