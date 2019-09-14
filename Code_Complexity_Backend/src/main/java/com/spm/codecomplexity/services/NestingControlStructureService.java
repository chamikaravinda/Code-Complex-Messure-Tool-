package com.spm.codecomplexity.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class NestingControlStructureService {
	public List<SingleLine> calculateComplexityDueNestingOfControlStructures(List<SingleLine> statmentList)
			throws Exception {
		
		int cnc = 0; //initialize the cn value
		boolean mainFlag = false;
		boolean elseFlag = false;
		boolean singleCommentFlag = false;
		boolean multiCommentFlagBegin = false;
		boolean multiCommentFlagEnd = false;
		
		for (SingleLine line : statmentList) {
			int count = 0;

			try {
				
				Pattern patternStructure = Pattern.compile(CommonConstants.MATCH_NESTING_CONTROL_STRUCTURE);
				Matcher matcherStructure = patternStructure.matcher(line.getStatement());
				
				Pattern patterBreakPoint = Pattern.compile(CommonConstants.MATCH_NESTING_CONTROL_BREAK);
				Matcher matcherBreakPoint = patterBreakPoint.matcher(line.getStatement());
				
				Pattern patterElse = Pattern.compile(CommonConstants.MATCH_NESTING_CONTROL_ELSE);
				Matcher matcherElse = patterElse.matcher(line.getStatement());
				
				Pattern patternSingleLineComment = Pattern.compile(CommonConstants.MATCH_NESTING_SINGLE_LINE_COMMENT);
				Matcher matcherSingleLineComment = patternSingleLineComment.matcher(line.getStatement());
							
				Pattern patternMultiLineCommentBegin = Pattern.compile(CommonConstants.MATCH_NESTING_MULTI_LINE_COMMENT_BEGIN);
				Matcher matcherMultiLineCommentBegin = patternMultiLineCommentBegin.matcher(line.getStatement());
				
				Pattern patternMultiLineCommentEnd = Pattern.compile(CommonConstants.MATCH_NESTING_MULTI_LINE_COMMENT_END);
				Matcher matcherMultiLineCommentEnd = patternMultiLineCommentEnd.matcher(line.getStatement());
				
				while(matcherStructure.find()) {
					++cnc;
					mainFlag = true;
				}
				
				while(matcherBreakPoint.find()) {
					--cnc;
					mainFlag = true;
				}
			
				
				while(matcherElse.find()) {
					elseFlag = true;
					mainFlag = true;
				}
				
				while( matcherSingleLineComment.find()) {
				
					if( mainFlag ) {
						singleCommentFlag = false;
						mainFlag = false;
					}else {
						singleCommentFlag = true;
						mainFlag = false;
					}
				}
				
				while( matcherMultiLineCommentBegin.find() ) {
					multiCommentFlagBegin = true;
				}
				
				while( matcherMultiLineCommentEnd.find() ) {
					multiCommentFlagEnd = true;
				}

			} catch (Exception e) {
				System.out.println("Exceptoin : " + e);
			}

			if( cnc < 0 ) {
				cnc = 0;
			}
			
		
			
			System.out.println("nester line : " + line.getStatement());
			if( /*line.getStatement().isBlank() || */line.getStatement().isEmpty()) {
				System.out.println("null statement");
				line.setCnc(0);
			}else {
				if(elseFlag) {
					line.setCnc(0);
					elseFlag = false;
				}else {
					
					if(singleCommentFlag ) {
						line.setCnc(0);
						singleCommentFlag = false;
					}else
						if( multiCommentFlagBegin ) {
							
							if( multiCommentFlagEnd ) {
								line.setCnc(0);
								multiCommentFlagBegin = false;
								multiCommentFlagEnd = false;
							}
							
						
						}else {
							line.setCnc(cnc);
						}
				
				}
				
			}
			
		
		}
		return statmentList;
	}
}
