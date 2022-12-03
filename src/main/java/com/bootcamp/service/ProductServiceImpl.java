package com.bootcamp.service;

import com.bootcamp.entity.Product;
import com.bootcamp.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRespository productRepository;

    @Override
    public Flux<Product> getAll() {
        Flux<Product> productFlux = productRepository.findAll();
        return productFlux;
    }

    @Override
    public Mono<Product> getOne(String name) {
        Mono<Product> pro = productRepository.findAll().filter( x -> x.getName().equals(name)
        ).next();
        return pro;
    }

    @Override
    public Mono<Product> save(Product prod) {
        return productRepository.save(prod);
    }

    @Override
    public Mono<Product> update(String name, String newName) {
        Mono<Product> productMono = getOne(name);

        Product productNew = productMono.block();

        productNew.setName(newName);

        return productRepository.save(productNew);
    }

    @Override
    public Mono<Void> delete(String name) {

        Mono<Product> productMono = getOne(name);

        Product productNew = productMono.block();

        return productRepository.delete(productNew);
    }
}
