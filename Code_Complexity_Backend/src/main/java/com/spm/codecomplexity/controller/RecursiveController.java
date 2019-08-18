package com.spm.codecomplexity.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.dao.UploadRepo;
import com.spm.codecomplexity.exceptions.CustomErrorResponse;
import com.spm.codecomplexity.model.Upload;
import com.spm.codecomplexity.services.RecursiveService;
import com.spm.codecomplexity.services.StatmentSizeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class RecursiveController {

	@Autowired
	UploadRepo uploadRepo;

	@Autowired
	StatmentSizeService statmentSizeService;

	@Autowired
	RecursiveService recursiveService;
	
	@GetMapping("/recursive/analyse/{id}")
	public ResponseEntity<Object> analyseRecursive(@PathVariable String id) {

		int size = 0;
		try {
			
			size= recursiveService.getRecursiveSize(id);
			return new ResponseEntity<Object>(size, HttpStatus.OK);

		}catch (Exception e) {
			
			System.out.println("Error " + e);
			e.printStackTrace();
			CustomErrorResponse errors = new CustomErrorResponse();
			errors.setError("Unknown error");
			errors.setStatus(HttpStatus.BAD_GATEWAY.value());

			return new ResponseEntity<>(errors, HttpStatus.BAD_GATEWAY);
		}
		
	}
}
