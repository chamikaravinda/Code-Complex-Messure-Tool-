package com.spm.codecomplexity.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.spm.codecomplexity.model.User;

public interface UserRepo extends MongoRepository<User, String>{
	
	User findByemail(String email);
	User findBy_id(ObjectId id);
}
