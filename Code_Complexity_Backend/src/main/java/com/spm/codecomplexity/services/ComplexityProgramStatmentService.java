package com.spm.codecomplexity.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplexityProgramStatmentService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<SingleLine> calculateComplexityOfProgramStatment(List<SingleLine> statmentList){ 
		LOG.info("DATA Recived for the Complexity Program Statment Service Method");

		for(SingleLine line :statmentList) {
			line.setCps(line.getCs()*line.getTw());
		}
		
		return statmentList;
	}
	
}
