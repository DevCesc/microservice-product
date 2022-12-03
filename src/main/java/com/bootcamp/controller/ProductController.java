package com.bootcamp.controller;

import com.bootcamp.entity.Product;
import com.bootcamp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/getAll")
    private Flux<Product> getAll(){
        return productService.getAll();
    }
    @GetMapping(value = "/getOne/{name}")
    private Mono<Product> Get_One(@PathVariable("name") String name){
        return productService.getOne(name);
    }

    @PostMapping(value = "/save")
    private Mono<Product> save(@RequestBody Product prod){
        return productService.save(prod);
    }

    @PutMapping(value = "/update/{name}/{newname}")
    private Mono<Product> update(@PathVariable("name") String name, @PathVariable("newname") String newName){
        return productService.update(name,newName);
    }

    @DeleteMapping(value = "/delete/{name}")
    private Mono<Void> delete(@PathVariable("name") String name){
        return productService.delete(name);
    }

}
