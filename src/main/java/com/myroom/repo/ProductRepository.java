package com.myroom.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myroom.model.ProductDetails;

public interface ProductRepository extends MongoRepository<ProductDetails, Integer> {
List<ProductDetails> findByUserId(int userId);

}
