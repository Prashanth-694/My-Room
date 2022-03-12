package com.myroom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.myroom.model.ProductDetails;
import com.myroom.model.Users;
import com.myroom.repo.UsersRepo;
import com.myroom.service.MyRoomService;
@CrossOrigin(origins = "*")
@RestController
public class MyRoomController {
@Autowired
UsersRepo usersRepo;

@Autowired
MyRoomService myRoomService;
@PostMapping("/insertUsers")
public Users insertUsers(@RequestParam("img") MultipartFile file) throws IOException {
	System.out.println("inside insert method");
	System.out.println(file.getBytes());
	Users users=new Users(2,"pm","email","pass","role",file.getBytes());
	return usersRepo.save(users);
}

@GetMapping("/fetchUsers")
public List<Users> fetchUsers() {
	return usersRepo.findAll();
}
@PostMapping("/insertProductDetails")
public ProductDetails insertProductDetails(@RequestBody ProductDetails details)
{
return	myRoomService.insertProducts(details);
}

}

