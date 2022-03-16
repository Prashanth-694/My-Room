package com.myroom.controller;


//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.myroom.model.ProductDetails;
//import com.myroom.model.Users;
import com.myroom.repo.UsersRepo;
//import com.myroom.service.MessageService;
import com.myroom.service.MyRoomService;
import com.myroom.service.UsersService;
@CrossOrigin(origins = {"*", "https://myroom401.herokuapp.com"})
@RestController
public class MyRoomController {
@Autowired
UsersRepo usersRepo;

@Autowired
MyRoomService myRoomService;

//@Autowired   
//MessageService messageService;

@Autowired
UsersService service;

@GetMapping("/userDetails/{userId}")
public List<ProductDetails> fetchUsers(@PathVariable int userId) {
	return myRoomService.fetchProductDetailsById(userId);
}

@PostMapping("/insertProductDetails")
public ProductDetails insertProductDetails(@RequestBody ProductDetails details)
{
	ProductDetails productDetails =myRoomService.insertProducts(details);
//	Users users=service.fetchById(details.getUserId());
//	System.out.println(users.getUserName());
//	messageService.sendMessage(users.getUserName(),details.getItemName(),details.getDescription(), details.getAmount(), details.getCreatedDate());
	return productDetails;

}

@PostMapping("/updateproductDetails")
public ProductDetails updateproductDetails(@RequestBody ProductDetails details) {
	return myRoomService.updateProductDetails(details);
}



}

