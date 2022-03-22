package com.myroom.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myroom.model.Users;

public interface UsersRepo extends MongoRepository<Users, Integer>{
	
	Users findByEmailIdAndPassword(String emailId, String password); 
	Users findByEmailId(String emailId);
}
