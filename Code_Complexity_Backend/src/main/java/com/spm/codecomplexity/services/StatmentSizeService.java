package com.spm.codecomplexity.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class StatmentSizeService {

	public List<SingleLine> calculateComplexityDueToStatmentSize(List<SingleLine> statmentList)
			throws Exception {

		for (SingleLine line : statmentList) {
			int count = 0;
			try {
				String regex = CommonConstants.ARITHMETIC_OPERATORS + "|" + CommonConstants.RELATIONAL_OPERATORS + "|"
						+ CommonConstants.LOGICAL_OPERATORS + "|" + CommonConstants.BITWISE_OPERATORS + "|"
						+ CommonConstants.MISCELLANEOUS_OPERATORS + "|" + CommonConstants.ASSIGNMENT_OPERATORS + "|"
						+ CommonConstants.KEYWORDS + "|" + CommonConstants.TEXT_INSIDE_QUOTES + "|"
						+ CommonConstants.NUMERIC_VALUES + "|" + CommonConstants.MANIPULATORS;

				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(line.getStatement());

				while (matcher.find()) {
					count++;
					// System.out.println("found: " + count + " : " + matcher.start() + " - " +
					// matcher.end());
				}

				line.setCs(count);
			} catch (Exception e) {
				System.out.println("Exception : " + e);
			}

		}
		return statmentList;
	}
}
