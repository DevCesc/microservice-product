package com.bootcamp.controller;

import com.bootcamp.entity.Product;
import com.bootcamp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping(value = "/findAll")
    public Mono<ResponseEntity<Flux<Product>>> findAll(){
        Flux<Product> fx = service.findAll();
        return Mono.just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fx))
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/findById/{id}")
    public  Mono<ResponseEntity<Product>> findById(@PathVariable("id") String id){
        return service.findById(id)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/save")
    public Mono<ResponseEntity<Product>> save(@RequestBody Product product, final ServerHttpRequest req){
        return service.save(product)
                .map(e -> ResponseEntity
                        .created(URI.create(req.getURI().toString().concat("/").concat(e.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e)
                );
    }

    @PutMapping(value = "/update/{id}")
    public Mono<ResponseEntity<Product>> update(@PathVariable("id") String id, @RequestBody Product product){
        product.setId(id);

        Mono<Product> monoBody = Mono.just(product);
        Mono<Product> monoDB = service.findById(id);

        return monoDB.zipWith(monoBody, (db, p) -> {
                    db.setId(id);
                    db.setType(p.getType());
                    db.setName(p.getName());
                    db.setDescription(p.getDescription());
                    db.setStatus(p.getStatus());
                    return db;
                })
                .flatMap(service::update)
                .map(e -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(e))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") String id){

        return service.findById(id)
                .flatMap(e -> service.delete(e.getId()))
                        .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
