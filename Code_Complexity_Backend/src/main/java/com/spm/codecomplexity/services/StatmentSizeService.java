package com.spm.codecomplexity.services;

import java.util.ArrayList;
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

	// regex for all words
	String allWordRegex = CommonConstants.ALL_WORDS;
	

	// regex for import statment
	String importStatment = CommonConstants.IMPORT_STATMETN;

	// ignoring list
	List<String> toIgnore = new ArrayList<>();

	// Complexity score holder
	int count = 0;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public List<SingleLine> calculateComplexityDueToStatmentSize(List<SingleLine> statmentList) throws Exception {

		LOG.info("DATA Recived for the Statment Size service layer method");

		// set ignore List
		setIgnoreList();

		// identified tokenlist
		List<String> identifiedTokens = new ArrayList<String>();

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
				
				Pattern ImportStatmentpattern = Pattern.compile(importStatment);
				Matcher ImportStatmentmatcher = ImportStatmentpattern.matcher(line.getStatement());


				// all words
				Pattern allWordPattern = Pattern.compile(allWordRegex);
				
				if(ImportStatmentmatcher.find()) {
					continue;
				}

				if (!isAcommentLine) {
					if (isSingleLineComment) {

						String uncommentedPart = line.getStatement().substring(0, singleLineCommentmatcher.start());

						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(uncommentedPart);
						while (matcher.find()) {
							identifiedTokens.add(matcher.group());
							count++;
						}

						Matcher allWordmatcher = allWordPattern.matcher(uncommentedPart);
						while (allWordmatcher.find()) {

							List<String> tempList = new ArrayList<>();
							tempList.add(allWordmatcher.group());

							for (String temp : tempList) {
								if (!toIgnore.contains(temp)) {
									// check not a number
									String string = temp;
									boolean numeric = true;
									numeric = string.matches("-?\\d+(\\.\\d+)?");
									if (!numeric) {
										identifiedTokens.add(temp);
										count++;
									}
								}
							}

						}
						line.setCs(count);

					} else if (MultiLineCommentStartmatcher.find()) {
						String uncommentedPartBefor = line.getStatement().substring(0,
								MultiLineCommentStartmatcher.start());
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(uncommentedPartBefor);
						while (matcher.find()) {
							identifiedTokens.add(matcher.group());
							count++;
						}
						line.setCs(count);
						isAcommentLine = true;

						Matcher allWordmatcher = allWordPattern.matcher(uncommentedPartBefor);
						while (allWordmatcher.find()) {

							List<String> tempList = new ArrayList<>();
							tempList.add(allWordmatcher.group());

							for (String temp : tempList) {
								if (!toIgnore.contains(temp)) {
									// check not a number
									String string = temp;
									boolean numeric = true;
									numeric = string.matches("-?\\d+(\\.\\d+)?");
									if (!numeric) {
										identifiedTokens.add(temp);
										count++;
									}
								}
							}

						}

						if (MultiLineCommentEndmatcher.find()) {
							String uncommentedPartAfter = line.getStatement()
									.substring(MultiLineCommentEndmatcher.end());
							Pattern pattern_end = Pattern.compile(regex);
							Matcher matcher_end = pattern_end.matcher(uncommentedPartAfter);
							while (matcher_end.find()) {
								identifiedTokens.add(matcher.group());
								count++;
							}

							Matcher allWordmatcherAfter = allWordPattern.matcher(uncommentedPartAfter);
							while (allWordmatcherAfter.find()) {

								List<String> tempList = new ArrayList<>();
								tempList.add(allWordmatcherAfter.group());

								for (String temp : tempList) {
									if (!toIgnore.contains(temp)) {
										// check not a number
										String string = temp;
										boolean numeric = true;
										numeric = string.matches("-?\\d+(\\.\\d+)?");
										if (!numeric) {
											identifiedTokens.add(temp);
											count++;
										}
									}
								}

							}

							line.setCs(count);
							isAcommentLine = false;
						}

					} else {
						Pattern pattern = Pattern.compile(regex);
						Matcher matcher = pattern.matcher(line.getStatement());
						while (matcher.find()) {
							identifiedTokens.add(matcher.group());
							count++;
						}

						Matcher allWordmatcherAfter = allWordPattern.matcher(line.getStatement());
						while (allWordmatcherAfter.find()) {

							List<String> tempList = new ArrayList<>();
							tempList.add(allWordmatcherAfter.group());

							for (String temp : tempList) {
								if (!toIgnore.contains(temp)) {
									// check not a number
									String string = temp;
									boolean numeric = true;
									numeric = string.matches("-?\\d+(\\.\\d+)?");
									if (!numeric) {
										identifiedTokens.add(temp);
										count++;
									}
								}
							}

						}

						line.setCs(count);
					}
				} else {
					if (MultiLineCommentEndmatcher.find()) {
						String uncommentedPartAfter = line.getStatement().substring(MultiLineCommentEndmatcher.end());
						Pattern pattern_end = Pattern.compile(regex);
						Matcher matcher_end = pattern_end.matcher(uncommentedPartAfter);
						while (matcher_end.find()) {
							identifiedTokens.add(matcher_end.group());
							count++;
						}

						Matcher allWordmatcher = allWordPattern.matcher(uncommentedPartAfter);
						while (allWordmatcher.find()) {

							List<String> tempList = new ArrayList<>();
							tempList.add(allWordmatcher.group());

							for (String temp : tempList) {
								if (!toIgnore.contains(temp)) {
									// check not a number
									String string = temp;
									boolean numeric = true;
									numeric = string.matches("-?\\d+(\\.\\d+)?");
									if (!numeric) {
										identifiedTokens.add(temp);
										count++;
									}
								}
							}

						}

						line.setCs(count);
						isAcommentLine = false;
					}
				}
			} catch (Exception e) {
				LOG.info("Error in Calculating Statment Size Complexity : " + e.getMessage());
				e.printStackTrace();
			}
			count = 0;
			for (String identifier : identifiedTokens) {
				line.setTokensOnStatmentSize(identifier);
			}
			identifiedTokens.clear();
		}
		return statmentList;
	}

	public void setIgnoreList() {
		toIgnore.add("public");
		toIgnore.add("private");
		toIgnore.add("protected");
		toIgnore.add("static");
		toIgnore.add("else");
		toIgnore.add("try");
		toIgnore.add("return");
		toIgnore.add("catch");
		toIgnore.add("int");
		toIgnore.add("float");
		toIgnore.add("long");
		toIgnore.add("double");
		toIgnore.add("print");
		toIgnore.add("System");
		toIgnore.add("out");
		toIgnore.add("void");
		toIgnore.add("String");
		toIgnore.add("println");
		toIgnore.add("if");
		toIgnore.add("for");
		toIgnore.add("while");
		toIgnore.add("do");
		toIgnore.add("switch");
		toIgnore.add("case");
		toIgnore.add("main");
		toIgnore.add("args");


	}
}
