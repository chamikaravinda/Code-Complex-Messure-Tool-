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
import com.spm.codecomplexity.services.StatmentSizeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class StatmentSizeController {

	@Autowired
	UploadRepo uploadRepo;

	@Autowired
	StatmentSizeService statmentSizeService;

	@GetMapping("/statmentsize/analyse/{id}")
	public ResponseEntity<Object> analyseStatmetSize(@PathVariable String id) {

		Upload file = uploadRepo.findBy_id(id);
		File readFile = new File(getClass().getClassLoader().getResource("tempFile.txt").getFile());

		BufferedReader reader=null;
		OutputStream os=null;
		
		int size = 0;
		try {

			os = new FileOutputStream(readFile);
			os.write(file.getFile());
			reader = new BufferedReader(new FileReader(readFile));
			String line = reader.readLine();
			
			while (line != null) {
				line = reader.readLine();
				size += statmentSizeService.calculateComplexityDueToStatmentSize(line);
			}
			
			os.close();
			reader.close();
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
