package com.myroom.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.model.ProductDetails;
import com.myroom.repo.ProductRepository;

@Service
public class MyRoomService {
	@Autowired
	ProductRepository productRepository;

	public ProductDetails insertProducts(ProductDetails productDetails) {
		System.out.println("inside ProductDetails Sercvice insert");
		return productRepository.save(productDetails);
	}

	public List<ProductDetails> fetchProductDetailsById(int userId) {
		System.out.println("Requested " + userId);
		return productRepository.findByUserId(userId);
	}

}
