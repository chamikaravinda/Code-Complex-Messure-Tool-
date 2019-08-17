package com.spm.codecomplexity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.dao.UserRepo;
import com.spm.codecomplexity.model.User;
import com.taxperts.webapp.exceptions.CustomErrorResponse;
import com.taxperts.webapp.exceptions.CustomException;


@RestController
@RequestMapping("/codecm")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	UserRepo userRepo;

	@PostMapping("/user/add")
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		try {
			User isAlredyExistUser = userRepo.findByemail(user.getEmail());

			if (isAlredyExistUser != null) {
				throw new CustomException("The email :" + user.getEmail() + "is Already exists");
			}

			String id = userRepo.save(user).get_id();
			
			return new ResponseEntity<Object>(user, HttpStatus.OK);

		} catch (CustomException e) {
			
			System.out.println("Error" + e.getMessage());
			
			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setError(e.getMessage());
			errors.setStatus(HttpStatus.CONFLICT.value());

			return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			
			System.out.println("Error" + e.getMessage());
			
			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setError("Unknown error");
			errors.setStatus(HttpStatus.BAD_GATEWAY.value());

			return new ResponseEntity<>(errors, HttpStatus.BAD_GATEWAY);
		}
	}

}
