package com.bootcamp.repository;

import com.bootcamp.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRespository extends ReactiveCrudRepository<Product, ObjectId> {

}
