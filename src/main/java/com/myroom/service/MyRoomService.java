package com.myroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.model.ProductDetails;
import com.myroom.repo.ProductRepository;

@Service
public class MyRoomService {
@Autowired
ProductRepository productRepository;

public ProductDetails insertProducts(ProductDetails productDetails)
{
	System.out.println("inside ProductDetails Sercvice insert");
	return productRepository.save(productDetails);	
}

public void fetchProductDetailsById(int userId) {
	System.out.println("Requested "+userId);
	productRepository.findById(userId);
}

}
