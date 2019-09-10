package com.spm.codecomplexity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CodeLineStack;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class RecursiveService {

	//stack that hold the all matched objects
	@Autowired
	CodeLineStack codeLineStack;
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	  
	  
	public List<SingleLine> calculateComplexityDueToRecurtion(List<SingleLine> statmentList) throws Exception {
		
		LOG.info("DATA Recived for the Recurtion Calculation Service Method");
		
		//Check what are the method names available 
		for (SingleLine line : statmentList) {
			String regex = CommonConstants.METHOD_DEFINITIONS_IDENTIFIER;

			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(line.getStatement());
			
			while (matcher.find()) {
				System.out.println("Match found");
				codeLineStack.push(matcher.group());
			}
			System.out.println("These are all the methods"+codeLineStack.toString());
		}
		return statmentList;
	}

}
