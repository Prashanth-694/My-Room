package com.myroom.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.myroom.model.ProductDetails;
import com.myroom.repo.UsersRepo;
import com.myroom.service.MyRoomService;
@CrossOrigin(origins = {"*", "http://5thfloor401.ccbp.tech"})
@RestController
public class MyRoomController {
@Autowired
UsersRepo usersRepo;

@Autowired
MyRoomService myRoomService;


@GetMapping("/userDetails/{userId}")
public List<ProductDetails> fetchUsers(@PathVariable int userId) {
	return myRoomService.fetchProductDetailsById(userId);
}

@PostMapping("/insertProductDetails")
public ProductDetails insertProductDetails(@RequestBody ProductDetails details)
{
return	myRoomService.insertProducts(details);
}


}

