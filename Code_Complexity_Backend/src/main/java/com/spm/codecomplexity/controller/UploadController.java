package com.spm.codecomplexity.controller;

import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.spm.codecomplexity.dao.UploadRepo;
import com.spm.codecomplexity.model.Upload;
import com.spm.codecomplexity.util.Generator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class UploadController {

	@Autowired
	UploadRepo repo;
	
	@RequestMapping(value = "/upload/file", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Object> submitAssignment(@RequestParam("file") MultipartFile file
			)
			throws IOException {
		
		Upload upload = new Upload();
		
		upload.set_id(new ObjectId());
		upload.setFile(file.getBytes());
		upload.setFileName(file.getOriginalFilename());
		upload.setUploadDate(Generator.getCurrentDate());
		upload.setUploadTime(Generator.getCurrentTime());
		
		return new ResponseEntity<>(repo.save(upload), HttpStatus.OK);
		
	}
	
	@GetMapping("/file/all")
	public List<Upload> getAllFiles(){
		return repo.findAll();
	}
	
	@GetMapping("/file/single/{id}")
	public Upload getSingleFileDetail( @PathVariable String id) {
		
		return repo.findBy_id(id);
	}
	
	@GetMapping("/test")
	public String testAPI() {
		return "working";
	}
}
