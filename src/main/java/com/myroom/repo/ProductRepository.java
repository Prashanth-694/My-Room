package com.myroom.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myroom.model.ProductDetails;

public interface ProductRepository extends MongoRepository<ProductDetails, Integer> {

}
