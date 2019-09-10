package com.spm.codecomplexity.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spm.codecomplexity.model.SingleLine;
import com.spm.codecomplexity.util.CommonConstants;

@Service
public class StatmentSizeService {

	// flag to avoid comment lines getting calculated
	private boolean isAcommentLine = false;

	// regex pattern for the code size;
	String regex = CommonConstants.ARITHMETIC_OPERATORS + "|" + CommonConstants.RELATIONAL_OPERATORS + "|"
			+ CommonConstants.LOGICAL_OPERATORS + "|" + CommonConstants.BITWISE_OPERATORS + "|"
			+ CommonConstants.MISCELLANEOUS_OPERATORS + "|" + CommonConstants.ASSIGNMENT_OPERATORS + "|"
			+ CommonConstants.KEYWORDS + "|" + CommonConstants.TEXT_INSIDE_QUOTES + "|" + CommonConstants.NUMERIC_VALUES
			+ "|" + CommonConstants.MANIPULATORS;

	// regex for the single line comment
	String singleLineRegex = CommonConstants.SINGLE_LINE_COMMENT;

	// regex for the multi line comment start
	String multiLineRegexStart = CommonConstants.MULTI_LINE_COMMENT_START;

	// regex for the multi line comment end
	String multiLineRegexEnd = CommonConstants.MULTI_LINE_COMMENT_END;

	// Complexity score holder
	int count = 0;


	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	

	public List<SingleLine> calculateComplexityDueToStatmentSize(List<SingleLine> statmentList) throws Exception {

		LOG.info("DATA Recived for the Statment Size service layer method");
		
		for (SingleLine line : statmentList) {
			try {
				// check for the comments
				Pattern singleLineCommentpattern = Pattern.compile(singleLineRegex);
				Matcher singleLineCommentmatcher = singleLineCommentpattern.matcher(line.getStatement());
				Boolean isSingleLineComment = singleLineCommentmatcher.find();

				
				Pattern MultiLineCommentStartpattern = Pattern.compile(multiLineRegexStart);
				Matcher MultiLineCommentStartmatcher = MultiLineCommentStartpattern.matcher(line.getStatement());

				Pattern MultiLineCommentEndpattern = Pattern.compile(multiLineRegexEnd);
				Matcher MultiLineCommentEndmatcher = MultiLineCommentEndpattern.matcher(line.getStatement());

				if (!isAcommentLine) {
					if (isSingleLineComment) {
						
						String uncommentedPart = line.getStatement().substring(0, singleLineCommentmatcher.start());
						
						
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(uncommentedPart);
						while (matcher.find()) {
							count++;
						}
						line.setCs(count);

					} else if (MultiLineCommentStartmatcher.find()) {
						String uncommentedPartBefor = line.getStatement().substring(0,
								MultiLineCommentStartmatcher.start());
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(uncommentedPartBefor);
						while (matcher.find()) {
							count++;
						}
						line.setCs(count);
						isAcommentLine = true;
						if (MultiLineCommentEndmatcher.find()) {
							String uncommentedPartAfter = line.getStatement().substring(MultiLineCommentEndmatcher.end());
							Pattern pattern_end = Pattern.compile(regex);
							Matcher matcher_end = pattern_end.matcher(uncommentedPartAfter);
							while (matcher_end.find()) {
								count++;
							}
							line.setCs(count);
							isAcommentLine = false;
						}

					} else {
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(line.getStatement());
						while (matcher.find()) {
							count++;
						}
						line.setCs(count);
					}
				} else {
					if (MultiLineCommentEndmatcher.find()) {
						String uncommentedPartAfter = line.getStatement().substring(MultiLineCommentEndmatcher.end());
						Pattern pattern_end = Pattern.compile(regex);
						Matcher matcher_end = pattern_end.matcher(uncommentedPartAfter);
						while (matcher_end.find()) {
							count++;
						}
						line.setCs(count);
						isAcommentLine = false;
					}
				}

			} catch (Exception e) {
				System.out.println("Exception : " + e);
				e.printStackTrace();
			}
			count = 0;
		}
		return statmentList;
	}
}
