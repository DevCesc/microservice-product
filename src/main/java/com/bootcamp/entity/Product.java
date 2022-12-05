package com.bootcamp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "product")
public class Product {

    @EqualsAndHashCode.Include
    @Id
    private String id;
    private String type;
    private String name;
    private String description;
    private Boolean status;
}
