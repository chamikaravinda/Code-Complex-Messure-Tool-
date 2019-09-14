package com.spm.codecomplexity.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class ControlStructureService {


	public List<SingleLine> calculateComplexityDueToControlStructures(List<SingleLine> statmentList)
			throws Exception {
		
		boolean switchFlag = false;
		int switchIndex = 0;
		int index = 0;
		int caseCount = 0;
		int switchReturnVal = 0;
		
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		
		for (SingleLine line : statmentList) {
			int count = 0;

			try {
				Pattern patternIf = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_IF);
				Matcher matcherIf = patternIf.matcher(line.getStatement());

				Pattern patternLoop = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_LOOP);
				Matcher matchLoop = patternLoop.matcher(line.getStatement());

				Pattern patternCatch = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_CATCH);
				Matcher matchCatch = patternCatch.matcher(line.getStatement());
				
				Pattern patternSwitch = Pattern.compile(CommonConstants.MATCH_KEYWORD_SWITCH);
				Matcher matchSwitch= patternSwitch.matcher(line.getStatement());
				
				Pattern patterBreakPoint = Pattern.compile(CommonConstants.MATCH_NESTING_CONTROL_BREAK);
				Matcher matcherBreakPoint = patterBreakPoint.matcher(line.getStatement());
				
				Pattern patternCase = Pattern.compile(CommonConstants.MATCH_KEYWORD_CASE);
				Matcher matcherCase = patternCase.matcher(line.getStatement());
				
				while(matchSwitch.find()) {
					switchFlag = true;
					switchIndex = index;
				}
				
				while(matcherBreakPoint.find()) {
					System.out.println("break point detected");
					switchFlag = false;
					hmap.put(index, caseCount);
					switchReturnVal = caseCount;
					caseCount = 0;
				}
				
				while( matcherCase.find() ) {
					System.out.println("case detected");
					++caseCount;
				}
				
			
				while (matcherIf.find()) {
					count++;
					// System.out.println("found: " + count + " : " + matcherIf.start() + " - " +
					// matcherIf.end());

					String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;

					Pattern innerPattern = Pattern.compile(innerKeyword);
					Matcher innerMatcher = innerPattern.matcher(line.getStatement());

					while (innerMatcher.find()) {
						++count;

					}

				}

				while (matchCatch.find()) {
					count++;
					// System.out.println("found: " + count + " : " + matcherIf.start() + " - " +
					// matcherIf.end());

					String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;

					Pattern innerPattern = Pattern.compile(innerKeyword);
					Matcher innerMatcher = innerPattern.matcher(line.getStatement());

					while (innerMatcher.find()) {
						++count;

					}

				}

				while (matchLoop.find()) {
					count += 2;
					// System.out.println("found: " + count + " : " + matcherIf.start() + " - " +
					// matcherIf.end());

					String innerKeyword = CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_BREAKS;

					Pattern innerPattern = Pattern.compile(innerKeyword);
					Matcher innerMatcher = innerPattern.matcher(line.getStatement());

					while (innerMatcher.find()) {
						count += 2;

					}

				}

			} catch (Exception e) {
				System.out.println("Exceptoin : " + e);
			}
			
			++index;
			//line.setCtc(switchReturnVal);
			
			if( switchReturnVal != 0 ) {
				line.setCtc(switchReturnVal);
				switchReturnVal = 0;
			}else {
				line.setCtc(count);
			}
			
			
		}
		
		
		
		return statmentList;
	}
}
