package com.bootcamp.service.impl;

import com.bootcamp.entity.Product;
import com.bootcamp.redisconfig.BDCacheConfig;
import com.bootcamp.repository.IProductRespository;
import com.bootcamp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductRespository productRepository;

    @Override
    @Cacheable(value = BDCacheConfig.CACHE_PRODUCT)
    public Flux<Product> findAll() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return productRepository.findAll();
    }

    @Override
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }
    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Product> update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Mono<Void> delete(String id) {
        return  productRepository.deleteById(id);
    }
}
