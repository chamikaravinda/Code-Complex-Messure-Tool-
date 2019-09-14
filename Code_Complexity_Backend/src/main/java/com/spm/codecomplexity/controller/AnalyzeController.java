package com.spm.codecomplexity.controller;

import java.util.ArrayList;
import java.util.List;

import com.spm.codecomplexity.model.Response;
import com.spm.codecomplexity.services.InheritenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.services.ComplexityProgramStatmentService;
import com.spm.codecomplexity.services.ControlStructureService;
import com.spm.codecomplexity.services.NestingControlStructureService;
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
	InheritenceService inheritenceService;

	@Autowired
	NestingControlStructureService nestingControlStructureService;

	@Autowired
	TotalWightService totalWightService;

	@Autowired
	RecursiveService recursiveService;

	@Autowired
	ComplexityProgramStatmentService complexityProgramStatmentService;

	private int  totalCs = 0;
	private int  totalCtc = 0;
	private int  totalCnc = 0;
	private int  totalCi = 0;
	private int  totalTW = 0;
	private int  totalCps = 0;
	private int  totalCr = 0;

	@GetMapping("/controlStructure/analyse/{id}")
	public Response analyseFile( @PathVariable String id ) {
		totalCs = 0;
		totalCtc = 0;
		totalCnc = 0;
		totalCi = 0;
		totalTW = 0;
		totalCps = 0;
		totalCr = 0;

		Response response = new Response();
		List<SingleLine> list = readfile.readFile(id);
		try {
			list=statmentSizeService.calculateComplexityDueToStatmentSize(list);
			list=controlStuctureService.calculateComplexityDueToControlStructures(list);
			list = inheritenceService.calculateComplexityDueToInheritenceStructures(list);
			list=nestingControlStructureService.calculateComplexityDueNestingOfControlStructures(list);
			list=totalWightService.calculateTotalWeight(list);
			list=complexityProgramStatmentService.calculateComplexityOfProgramStatment(list);
			list=recursiveService.calculateComplexityDueToRecurtion(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for(int x=0 ; x < list.size() ; x++) {
			totalCs += list.get(x).getCs();
			totalCtc += list.get(x).getCtc();
			totalCnc += list.get(x).getCnc();
			totalCi += list.get(x).getCi();
			totalTW += list.get(x).getTw();
			totalCps += list.get(x).getCps();
			totalCr += list.get(x).getCr();
		}

		response.setList(list);
		response.setTotalCs(totalCs);
		response.setTotalCtc(totalCtc);
		response.setTotalCnc(totalCnc);
		response.setTotalCi(totalCi);
		response.setTotalTW(totalTW);
		response.setTotalCps(totalCps);
		response.setTotalCr(totalCr);

		return response;
	}
	
}
