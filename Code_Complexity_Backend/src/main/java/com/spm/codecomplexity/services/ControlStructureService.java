package com.spm.codecomplexity.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class ControlStructureService {


	public List<SingleLine> calculateComplexityDueToControlStructures(List<SingleLine> statmentList)
			throws Exception {
		
		for (SingleLine line : statmentList) {
			int count = 0;

			try {
				Pattern patternIf = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_IF);
				Matcher matcherIf = patternIf.matcher(line.getStatement());

				Pattern patternLoop = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_LOOP);
				Matcher matchLoop = patternLoop.matcher(line.getStatement());

				Pattern patternCatch = Pattern.compile(CommonConstants.MATCH_CONTROL_STRUCTURE_TYPE_CATCH);
				Matcher matchCatch = patternCatch.matcher(line.getStatement());

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

			line.setCtc(count);
		}
		return statmentList;
	}
}
