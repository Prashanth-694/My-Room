package com.myroom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myroom.model.Users;
import com.myroom.repo.UsersRepo;

@Service
public class UsersService {
@Autowired
UsersRepo usersRepo;

public Users insertUsers(Users users)
{
return usersRepo.save(users);
}

public  List<Users> fetchAllUsers() {
	return usersRepo.findAll();
}

public Users fetchById(int id) {
	
	return usersRepo.findById(id).get();
}

public Users forgotPassword(String email,String password)
{
	Users user= usersRepo.findByEmailId(email);
	user.setPassword(password);
	usersRepo.save(user);
	return user;
}

//public  Users validUser(Users user) {
//	return usersRepo.findBy(user);
//}
}
