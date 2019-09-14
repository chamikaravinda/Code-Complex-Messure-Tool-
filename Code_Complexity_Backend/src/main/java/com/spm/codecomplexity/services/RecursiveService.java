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

	// stack that hold the all matched objects
	@Autowired
	CodeLineStack codeLineStack;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<SingleLine> calculateComplexityDueToRecurtion(List<SingleLine> statmentList) throws Exception {

		LOG.info("DATA Recived for the Recurtion Calculation Service Method");

		// Check what are the method names available
		for (SingleLine line : statmentList) {
			String regex = CommonConstants.METHOD_DEFINITION_IDENTIFIER;

			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(line.getStatement());

			while (matcher.find()) {
				System.out.println("Match found:"+ line.getStatement());
				codeLineStack.push(line);
			}
		}

		// asses each method is recursive method
		while (codeLineStack.size() != 0) {

			System.out.println("Matched stack not empty ");
			// to hold the bracket count
			int bracketCount = 1;

			boolean isRecursive = false;

			boolean isInsidetheMethod = true;
			
			int methodEndLine = 0;

			SingleLine checkMethodLine = codeLineStack.pop();

			String regex = CommonConstants.METHOD_NAME_IDENTIFIER;

			Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
			Matcher matcher = pattern.matcher(checkMethodLine.getStatement());

			while (matcher.find()) {
				// checking method name
				String methodname = matcher.group();
				
				String methodRegex = createMethodName(methodname);
				System.out.println("Method name : "+methodRegex);
				// checking is the method recursive
				for (SingleLine line : statmentList) {

					// check start from the beging of the fill and ignore if not a line of the
					// method
					if (checkMethodLine.getLineNumber()<line.getLineNumber() &&(methodEndLine==0 || line.getLineNumber()<methodEndLine)) {
						
						// check the method is there

						Pattern methodpattern = Pattern.compile(methodRegex);
						Matcher methodmatcher = methodpattern.matcher(line.getStatement());
						
						if(methodmatcher.find()) {
							if(isInsidetheMethod) {
								if(checkMethodLine.getLineNumber() != line.getLineNumber()) {
								isRecursive =  true;
								System.out.println("Recursive method found in line"+line.getLineNumber());
								}
							}
						}
						
						System.out.println("line belogns to the method");
						// check the file using method count
						String bracketregex = CommonConstants.CURLY_BRACKET_IDENTIFIER;

						Pattern bracketpattern = Pattern.compile(bracketregex, Pattern.MULTILINE);
						Matcher bracketmatcher = bracketpattern.matcher(line.getStatement());

						List<String> brackets = new ArrayList<>();
						while(bracketmatcher.find()) {
							System.out.println("bracket group found");
							brackets.add(bracketmatcher.group());
						}

						for (String bracket : brackets) {
							if (bracket.equals("}")) {
								System.out.println("Single bracket identified");
								if (bracketCount != 0) {
									bracketCount--;
									System.out.println(line.getLineNumber()+"}");
									if (bracketCount == 0) {
										isInsidetheMethod = false;
										methodEndLine=line.getLineNumber();
										System.out.println("Method end found in line:"+methodEndLine);
									}
								}
							} else if(bracket.equals("{")){
								System.out.println(line.getLineNumber()+"{");
								bracketCount++;
							}
						}

						
					}
				}
				//add the recursive value if true
				for(SingleLine rline : statmentList){
					if(isRecursive) {
						if(checkMethodLine.getLineNumber()<= rline.getLineNumber() && methodEndLine>= rline.getLineNumber()) {
							rline.setCr(rline.getCps()*2);
							System.out.println("Value doubled in:"+rline.getLineNumber());
						}
					}
				}
			}
			
			
		}

		return statmentList;
	}
	
	
	private String createMethodName(String str) {
	    if (str != null && str.length() > 0) {
	        str = str.substring(0, str.length() - 1);
	    }
	    return str;
	}
}
