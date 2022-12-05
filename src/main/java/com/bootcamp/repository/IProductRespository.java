package com.bootcamp.repository;

import com.bootcamp.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRespository extends ReactiveCrudRepository<Product, String> {

}
