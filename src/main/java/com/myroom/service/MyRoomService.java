package com.myroom.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.model.ProductDetails;
import com.myroom.model.Users;
import com.myroom.repo.ProductRepository;
import com.myroom.repo.UsersRepo;

@Service
public class MyRoomService {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UsersRepo usersRepo;
	int addAmount;

	public ProductDetails insertProducts(ProductDetails productDetails) {
		System.out.println("inside ProductDetails Sercvice insert");

		ProductDetails details = productRepository.save(productDetails);
		Users users = usersRepo.findById(productDetails.getUserId()).get();
		int total = productDetails.getAmount() + users.getSpentAmount();
		users.setSpentAmount(total);
		
		usersRepo.save(users);
		return details;
	}

	public List<ProductDetails> fetchProductDetailsById(int userId) {
		System.out.println("Requested " + userId);
		return productRepository.findByUserId(userId);
	}

}
