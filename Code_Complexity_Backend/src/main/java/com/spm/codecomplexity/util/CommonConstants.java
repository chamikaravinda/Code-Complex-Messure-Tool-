package com.spm.codecomplexity.util;

public class CommonConstants {

	//=================== Constants related to control structures =============================//
	
	private static final String MATCH_KEYWORD_IF = "if[(]|if\\s[(]*?";
	private static final String MATCH_KEYWORD_FOR = "for[(]|for\\s[(]*?";
	private static final String MATCH_KEYWORD_WHILE = "while[(]|while\\s[(]*?";
	private static final String MATCH_KEYWORD_CATCH = "catch[(]|catch\\s[(]*?";

	
	private static final String MATCH_DOUBLE_OR = "[|][|]";
	private static final String MATCH_DOUBLE_AND = "\\&&\\s";
	private static final String MATCH_OR = "[|]";
	private static final String MATCH_AND = "\\&\\s";
	
	public static final String MATCH_CONTROL_STRUCTURE_TYPE_IF = MATCH_KEYWORD_IF;
	
	public static final String MATCH_CONTROL_STRUCTURE_TYPE_CATCH = MATCH_KEYWORD_CATCH;
	
	public static final String MATCH_CONTROL_STRUCTURE_TYPE_LOOP = MATCH_KEYWORD_FOR + "|"
			+ MATCH_KEYWORD_WHILE;
	
	public static final String MATCH_CONTROL_STRUCTURE_TYPE_BREAKS = MATCH_DOUBLE_OR + "|" 
																	+ MATCH_DOUBLE_AND + "|"
																	+ MATCH_OR + "|"
																	+ MATCH_AND;
	//========================================================================================//
}
