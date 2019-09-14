package com.spm.codecomplexity.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.services.ComplexityProgramStatmentService;
import com.spm.codecomplexity.services.ControlStructureService;
import com.spm.codecomplexity.services.ReadFileService;
import com.spm.codecomplexity.services.RecursiveService;
import com.spm.codecomplexity.services.StatmentSizeService;
import com.spm.codecomplexity.services.TotalWightService;

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
	TotalWightService totalWightService;
	
	@Autowired
	RecursiveService recursiveService;
	
	@Autowired
	ComplexityProgramStatmentService complexityProgramStatmentService;
	
	@GetMapping("/controlStructure/analyse/{id}")
	public List<SingleLine> analyseFile( @PathVariable String id ) {
	
		List<SingleLine> list = readfile.readFile(id);
		
		try {
			list=statmentSizeService.calculateComplexityDueToStatmentSize(list);
			list=controlStuctureService.calculateComplexityDueToControlStructures(list);
			list=totalWightService.calculateTotalWeight(list);
			list=complexityProgramStatmentService.calculateComplexityOfProgramStatment(list);
			list=recursiveService.calculateComplexityDueToRecurtion(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
