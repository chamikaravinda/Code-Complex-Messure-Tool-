package com.spm.codecomplexity.controller;

import java.util.ArrayList;
import java.util.List;

import com.spm.codecomplexity.services.InheritenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.services.ControlStructureService;
import com.spm.codecomplexity.services.ReadFileService;
import com.spm.codecomplexity.services.RecursiveService;
import com.spm.codecomplexity.services.StatmentSizeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/codecm")
public class AnalyzeController {

	@Autowired
	ReadFileService readfile;
	
	@Autowired
	StatmentSizeService statmentSizeService;
	
	@Autowired
	ControlStructureService controlStuctureService;

	@Autowired
	InheritenceService inheritenceService;

	@Autowired
	RecursiveService recursiveService;

	@GetMapping("/controlStructure/analyse/{id}")
	public List<SingleLine> analyseFile( @PathVariable String id ) {
	
		List<SingleLine> list = readfile.readFile(id);
		
		try {
			list=statmentSizeService.calculateComplexityDueToStatmentSize(list);
			list=controlStuctureService.calculateComplexityDueToControlStructures(list);
			//list=recursiveService.calculateComplexityDueToRecurtion(list);
			//pass the list to your service here as done in the above line




		//pass the list to your service here as done in the above line
			// make your service read the list line by line and caculate the complexity and set the complexity using given set method
			//use getStament method in SingleLine object to read the line
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
