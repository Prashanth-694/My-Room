package com.myroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myroom.model.Users;
import com.myroom.repo.UsersRepo;
import com.myroom.service.UsersService;
@CrossOrigin(origins = "*")
@RestController
public class UsersController {

	@Autowired
	UsersService usersService;
	@Autowired
	UsersRepo repo;
	@PostMapping("/insertUsersDetails")
	public Users insertUsersDetails(@RequestBody Users users)
	{
		return usersService.insertUsers(users);
	}
	
	@GetMapping("/fetchAllUsers")
	public List<Users> fetchAllUsers()
	{
		return  usersService.fetchAllUsers();
	}
	@PostMapping("/validUser")
	public Users validUsersDetails(@RequestBody Users user)
	{	
		System.out.println(user);
		return repo.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
		
		
//		return usersService.validUser(user);
	}
	
}
