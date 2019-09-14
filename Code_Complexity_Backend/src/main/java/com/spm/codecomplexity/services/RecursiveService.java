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
				System.out.println("Match found");
				codeLineStack.push(line);
			}
		}

		// asses each method is recursive method
		while (codeLineStack.size() != 0) {

			System.out.println("Matched stack not empty ");
			// to hold the bracket count
			int bracketCount = 0;

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
				
				System.out.println("Method name : "+methodname);
				// checking is the method recursive
				for (SingleLine line : statmentList) {

					// check start from the beging of the fill and ignore if not a line of the
					// method
					if (line.getLineNumber() >= checkMethodLine.getLineNumber()) {
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
								if (bracketCount != 0) {
									bracketCount--;

									if (bracketCount == 0) {

										isInsidetheMethod = false;
										methodEndLine=line.getLineNumber();
										
									}
								}
							} else {
								bracketCount++;
							}
						}

						// check the method is there
						String methodregex = methodname;

						Pattern methodpattern = Pattern.compile("["+methodregex+"]");
						Matcher methodmatcher = methodpattern.matcher(methodregex);
						
						if(methodmatcher.find()) {
							if(isInsidetheMethod) {
								isRecursive =  true;
								System.out.println("Recursive method found");
							}
						}
					}
				}
			}
			
			//add the recursive value if true
			for(SingleLine rline : statmentList){
				if(isRecursive) {
					if(checkMethodLine.getLineNumber()<= rline.getLineNumber() && methodEndLine>= rline.getLineNumber()) {
						rline.setCr(rline.getCps()*2);
						System.out.println("Value doubled");
					}
				}
			}
		}

		return statmentList;
	}
}
