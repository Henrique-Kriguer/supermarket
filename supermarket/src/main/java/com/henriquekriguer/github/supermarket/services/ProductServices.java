package com.henriquekriguer.github.supermarket.services;

import com.henriquekriguer.github.supermarket.exception.ResourceNotFoundException;
import com.henriquekriguer.github.supermarket.model.Product;
import com.henriquekriguer.github.supermarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServices {

    private Logger logger = Logger.getLogger(ProductServices.class.getName());

    @Autowired
    ProductRepository repository;

    public List<Product> findAll(){
        logger.info("Finding all products!");
        return repository.findAll();
    }

    public Product findById(Long id){
        logger.info("finding one product by id!");
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
    }

    public Product create(Product product){
        logger.info("Creating a new product");
        // Force insert flow and let the database generate the primary key.
        product.setId(null);
        return repository.save(product);
    }

    public Product update(Product product){
        logger.info("Updating a product!");
        Product entity = repository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        entity.setDescription(product.getDescription());
        entity.setQuantity(product.getQuantity());
        entity.setUnit(product.getUnit());
        entity.setUnitPrice(product.getUnitPrice());
        return repository.save(entity);
    }
    public void delete(Long id){
        logger.info("Deleting a product by id!");
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id!"));
        repository.delete(entity);
    }

}
