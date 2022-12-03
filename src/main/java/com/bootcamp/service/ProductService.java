package com.bootcamp.service;

import com.bootcamp.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    public Flux<Product> getAll();
    public Mono<Product> getOne(String name);
    public Mono<Product> save(Product prod);
    public Mono<Product> update(String name, String newName);
    public Mono<Void> delete(String name);
}
