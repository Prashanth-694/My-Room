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
	@Autowired
	UsersService userService;	
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
		
		return productRepository.findByUserId(userId);
	}
	
	public ProductDetails updateProductDetails(ProductDetails productDetails)
	{    System.out.println(productDetails.getId());
		ProductDetails details=productRepository.findById(productDetails.getId());
		
		int beforeAmount=details.getAmount();
		
		details.setItemName(productDetails.getItemName());
		details.setDescription(productDetails.getDescription());
		details.setAmount(productDetails.getAmount());
		details.setCreatedDate(productDetails.getCreatedDate());
		 productRepository.save(details);
		int afterAmount=details.getAmount();
		
		int finalA=0;
		Users users = usersRepo.findById(productDetails.getUserId()).get();
		
		if(beforeAmount>afterAmount)
		{
			finalA=beforeAmount-afterAmount;
			int total=users.getSpentAmount()-finalA;
			users.setSpentAmount(total);
			usersRepo.save(users);
		}
		else if (beforeAmount<afterAmount) {
			finalA=afterAmount-beforeAmount;
			int total=users.getSpentAmount()+finalA;
			users.setSpentAmount(total);
			usersRepo.save(users);
		}
		 return details;
	}

	public  ProductDetails deleteById(ProductDetails productDetails) {
	
		ProductDetails details=	productRepository.findById(productDetails.getId());
		Users users = usersRepo.findById(details.getUserId()).get();
		int total=users.getSpentAmount()-details.getAmount();
		users.setSpentAmount(total);
		usersRepo.save(users);
		productRepository.delete(productDetails);
		
	return	productDetails;
	}
	public  void deleteallProducts() {
		
			productRepository.deleteAll();
//		List<Users> list	=usersRepo.findAll();
			List<Users> users = userService.fetchAllUsers();
			for(Users user : users) {
				user.setSpentAmount(0);
				usersRepo.save(user);
			}
		
	}

}
