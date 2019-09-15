package com.spm.codecomplexity.util;

public class CommonConstants {

	// =================== Constants related to control structures
	// =============================//

	private static final String MATCH_KEYWORD_IF = "if[(]|if\\s[(]*?";
	private static final String MATCH_KEYWORD_FOR = "for[(]|for\\s[(]*?";
	private static final String MATCH_KEYWORD_WHILE = "while[(]|while\\s[(]*?";
	private static final String MATCH_KEYWORD_CATCH = "catch[(]|catch\\s[(]*?";
	public static final String MATCH_KEYWORD_SWITCH = "switch[(]|switch\\s[(]*?";
	public static final String MATCH_KEYWORD_CASE = "case\\s";
	private static final String MATCH_ELSE = "else.*?";
	private static final String MATCH_RIGHT_CURLY_BRACKET = "[}]";

	private static final String MATCH_DOUBLE_OR = "[|][|]";
	private static final String MATCH_DOUBLE_AND = "\\&&\\s";
	private static final String MATCH_OR = "[|]";
	private static final String MATCH_AND = "\\&\\s";

	public static final String MATCH_CONTROL_STRUCTURE_TYPE_IF = MATCH_KEYWORD_IF;

	public static final String MATCH_CONTROL_STRUCTURE_TYPE_CATCH = MATCH_KEYWORD_CATCH;

	public static final String MATCH_CONTROL_STRUCTURE_TYPE_LOOP = MATCH_KEYWORD_FOR + "|" + MATCH_KEYWORD_WHILE;

	public static final String MATCH_CONTROL_STRUCTURE_TYPE_BREAKS = MATCH_DOUBLE_OR + "|" + MATCH_DOUBLE_AND + "|"
			+ MATCH_OR + "|" + MATCH_AND;

	// =================== Constants related to nesting control structures
	// =============================//
	public static final String MATCH_NESTING_CONTROL_STRUCTURE = MATCH_KEYWORD_IF + "|" + MATCH_KEYWORD_WHILE + "|"
			+ MATCH_KEYWORD_FOR + "|" + MATCH_ELSE + "|" + MATCH_KEYWORD_SWITCH;

	public static final String MATCH_NESTING_CONTROL_BREAK = MATCH_RIGHT_CURLY_BRACKET;

	public static final String MATCH_NESTING_CONTROL_ELSE = MATCH_ELSE;

	public static final String MATCH_NESTING_SINGLE_LINE_COMMENT = "(?<!http:|https:)\\/\\/.*(?<!\\>)$";
	public static final String MATCH_NESTING_MULTI_LINE_COMMENT_BEGIN = "([/][*])+";
	public static final String MATCH_NESTING_MULTI_LINE_COMMENT_END = "(?:.*)([*][/])+";

	// ========================================================================================//

	// =================== Constants related to code
	// size======================================//

	/*------------- weight values -----------------------*/

	public static final int SIZE_WEIGHT = 1;

	/*--------------Sub variables related to Arithmetic Operators-----------*/
	private static final String SUB_FIND_ASTERIC = "\\*\\s";
	private static final String SUB_FIND_PLUS = "\\+\\s";
	private static final String SUB_FIND_MINUS = "\\-\\s";
	private static final String SUB_FIND_FORWARD_SLASH = "\\/\\s";
	private static final String SUB_FIND_PERCENTAGE = "\\%\\s";
	private static final String SUB_FIND_DOUBLE_PLUS = "\\++.*?";
	private static final String SUB_FIND_DOUBLE_MINUS = "\\--.*?";

	/*--------------Sub variables related to Relational Operators --------------*/
	private static final String SUB_FIND_DOUBLE_EQUAL = "\\==\\s";
	private static final String SUB_FIND_NOT_EQUAL = "\\!=\\s";
	private static final String SUB_FIND_GREATER_THAN = "\\>\\s";
	private static final String SUB_FIND_LESS_THAN = "\\<\\s";
	private static final String SUB_FIND_GREATER_THAN_OR_EQUAL = "\\>=\\s";
	private static final String SUB_FIND_LESS_THAN_OR_EQUAL = "\\<=\\s";

	/*--------------Sub variables related to Logical Operators --------------*/
	private static final String SUB_FIND_DOUBLE_AND = "\\&&\\s";
	private static final String SUB_FIND_DOUBLE_OR = "[|][|]";
	private static final String SUB_FIND_EXCLAMATION = "\\!\\s";

	/*---------------sub variables related to bitwise operators----------------*/
	private static final String SUB_FIND_VERTICAL_LINE = "[|]\\s";
	private static final String SUB_FIND_UP_ARROW = "\\^\\s";
	private static final String SUB_FIND_WAVE_DASH = "\\~\\s";
	private static final String SUB_FIND_DOUBLE_LEFT_ARROW = "\\<<\\s";
	private static final String SUB_FIND_DOUBLE_RIGHT_ARROW = "\\>>\\s";
	private static final String SUB_FIND_TRIBLE_RIGHT_ARROW = "\\>>>\\s";
	private static final String SUB_FIND_TRIBLE_LEFT_ARROW = "\\<<<\\s";

	/*---------------sub variables related to Miscellaneous operators----------------*/
	private static final String SUB_FIND_QUOTATION = "\\'\\s";
	private static final String SUB_FIND_DASH_AND_ARROW = "\\->\\s";
	private static final String SUB_FIND_DOT = "[.]";
	private static final String SUB_FIND_DOUBLE_COLON = "\\::\\s";

	/*---------------sub variables related to assignment operators----------------*/
	private static final String SUB_FIND_PLUS_EQUAL = "\\+=\\s";
	private static final String SUB_FIND_MINUS_EQUAL = "\\-=\\s";
	private static final String SUB_FIND_ASTERIC_EQUAL = "\\*=\\s";
	private static final String SUB_FIND_DIVIDE_EQUAL = "\\/=\\s";
	private static final String SUB_FIND_EQUAL = "\\=\\s";
	private static final String SUB_FIND_TRIBLE_RIGHT_ARROW_EQUAL = "\\>>>=\\s";
	private static final String SUB_FIND_VERTICAL_EQUAL = "[|]=\\s";
	private static final String SUB_FIND_AND_EQUAL = "\\&=\\s";
	private static final String SUB_FIND_PERCENTAGE_EQUAL = "\\%=\\s";
	private static final String SUB_FIND_LEFT_DOUBLE_ARROW_EQUAL = "\\<<=\\s";
	private static final String SUB_FIND_RIGHT_DOUBLE_ARROW_EQUAL = "\\>>=\\s";
	private static final String SUB_FIND_UP_ARROW_EQUAL = "\\^=\\s";

	/*---------------sub variables related to keywords----------------*/
	private static final String SUB_FIND_KEYWORD_VOID = "void\\s";
	private static final String SUB_FIND_KEYWORD_DOUBLE = "double\\s";
	private static final String SUB_FIND_KEYWORD_LONG = "long\\s";
	private static final String SUB_FIND_KEYWORD_MAIN = "main.*?";
	private static final String SUB_FIND_KEYWORD_ARGS = "args.*?";
	private static final String SUB_FIND_KEYWORD_INT = "int\\s";
	private static final String SUB_FIND_KEYWORD_FLOAT = "float\\s";
	private static final String SUB_FIND_KEYWORD_STRING = "String\\s";
	private static final String SUB_FIND_KEYWORD_PRINTF = "printf.*?";
	private static final String SUB_FIND_KEYWORD_PRINTLN = "println.*?";
	private static final String SUB_FIND_KEYWORD_COUT = "cout.*?";
	private static final String SUB_FIND_KEYWORD_CIN = "cin.*?";
	private static final String SUB_FIND_KEYWORD_IF = "if.*?";
	private static final String SUB_FIND_KEYWORD_FOR = "for.*?";
	private static final String SUB_FIND_KEYWORD_WHILE = "while.*?";
	private static final String SUB_FIND_KEYWORD_DO_WHILE = "do[-]while.*?";
	private static final String SUB_FIND_KEYWORD_SWITCH = "switch.*?";
	private static final String SUB_FIND_KEYWORD_CASE = "case.*?";
	private static final String SUB_FIND_KEYWORD_SYSTEM = "System.*?";
	private static final String SUB_FIND_KEYWORD_OUT = "out.*?";

	/*---------------sub variables related to manipulators----------------*/
	private static final String SUB_FIND_ENDL = "endl\\s";
	private static final String SUB_FIND_NEW_LINE = "\\\n\\s";

	public static final String ARITHMETIC_OPERATORS = SUB_FIND_ASTERIC + "|" + SUB_FIND_PLUS + "|" + SUB_FIND_MINUS
			+ "|" + SUB_FIND_FORWARD_SLASH + "|" + SUB_FIND_PERCENTAGE + "|" + SUB_FIND_DOUBLE_PLUS + "|"
			+ SUB_FIND_DOUBLE_MINUS;

	public static final String RELATIONAL_OPERATORS = SUB_FIND_DOUBLE_EQUAL + "|" + SUB_FIND_NOT_EQUAL + "|"
			+ SUB_FIND_GREATER_THAN + "|" + SUB_FIND_LESS_THAN + "|" + SUB_FIND_GREATER_THAN_OR_EQUAL + "|"
			+ SUB_FIND_LESS_THAN_OR_EQUAL;

	public static final String LOGICAL_OPERATORS = SUB_FIND_DOUBLE_AND + "|" + SUB_FIND_DOUBLE_OR + "|"
			+ SUB_FIND_EXCLAMATION;

	public static final String BITWISE_OPERATORS = SUB_FIND_VERTICAL_LINE + "|" + SUB_FIND_UP_ARROW + "|"
			+ SUB_FIND_WAVE_DASH + "|" + SUB_FIND_DOUBLE_LEFT_ARROW + "|" + SUB_FIND_DOUBLE_RIGHT_ARROW + "|"
			+ SUB_FIND_TRIBLE_RIGHT_ARROW + "|" + SUB_FIND_TRIBLE_LEFT_ARROW;

	public static final String MISCELLANEOUS_OPERATORS = SUB_FIND_QUOTATION + "|" + SUB_FIND_DASH_AND_ARROW + "|"
			+ SUB_FIND_DOT + "|" + SUB_FIND_DOUBLE_COLON;

	public static final String ASSIGNMENT_OPERATORS = SUB_FIND_PLUS_EQUAL + "|" + SUB_FIND_MINUS_EQUAL + "|"
			+ SUB_FIND_ASTERIC_EQUAL + "|" + SUB_FIND_DIVIDE_EQUAL + "|" + SUB_FIND_EQUAL + "|"
			+ SUB_FIND_TRIBLE_RIGHT_ARROW_EQUAL + "|" + SUB_FIND_VERTICAL_EQUAL + "|" + SUB_FIND_AND_EQUAL + "|"
			+ SUB_FIND_PERCENTAGE_EQUAL + "|" + SUB_FIND_LEFT_DOUBLE_ARROW_EQUAL + "|"
			+ SUB_FIND_RIGHT_DOUBLE_ARROW_EQUAL + "|" + SUB_FIND_UP_ARROW_EQUAL;

	public static final String KEYWORDS = SUB_FIND_KEYWORD_VOID + "|" + SUB_FIND_KEYWORD_DOUBLE + "|"
			+ SUB_FIND_KEYWORD_INT + "|" + SUB_FIND_KEYWORD_FLOAT + "|" + SUB_FIND_KEYWORD_STRING + "|"
			+ SUB_FIND_KEYWORD_PRINTF + "|" + SUB_FIND_KEYWORD_PRINTLN + "|" + SUB_FIND_KEYWORD_COUT + "|"
			+ SUB_FIND_KEYWORD_CIN + "|" + SUB_FIND_KEYWORD_IF + "|" + SUB_FIND_KEYWORD_FOR + "|"
			+ SUB_FIND_KEYWORD_WHILE + "|" + SUB_FIND_KEYWORD_DO_WHILE + "|" + SUB_FIND_KEYWORD_SWITCH + "|"
			+ SUB_FIND_KEYWORD_CASE + "|" + SUB_FIND_KEYWORD_SYSTEM + "|" + SUB_FIND_KEYWORD_OUT + "|"
			+ SUB_FIND_KEYWORD_LONG + "|" + SUB_FIND_KEYWORD_ARGS + "|" + SUB_FIND_KEYWORD_MAIN;

	public static final String TEXT_INSIDE_QUOTES = "\"([^\"]*)\"";

	public static final String NUMERIC_VALUES = "[0]|[1-9][0-9]*";

	public static final String MANIPULATORS = SUB_FIND_ENDL + "|" + SUB_FIND_NEW_LINE;

	public static final String SINGLE_LINE_COMMENT = "(?<!http:|https:)\\/\\/.*(?<!\\>)$";

	public static final String MULTI_LINE_COMMENT_START = "([/][*])+";

	public static final String MULTI_LINE_COMMENT_END = "(?:.*)([*][/])+";

	// public static final String ALL_WORDS =
	// "(?<=^([^\"]|\"[^\"]*\")*)(\\b\\w+\\b)";
	public static final String ALL_WORDS = "\\w+(?=[^\"]*(\"[^\"]*\"[^\"]*)*$)";

	public static final String IMPORT_STATMETN = "(import)\\s+[\\w]+.";

	// ========================================================================================//

	// =================== Constants related to
	// Recursion======================================//

	public static final String METHOD_DEFINITION_IDENTIFIER = "(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])";

	public static final String METHOD_NAME_IDENTIFIER = "\\s*(\\w+)\\(.*?";

	public static final String CURLY_BRACKET_IDENTIFIER = "[{|}]";

	// =======================================================================================//
	public static final String METHOD_DEFINITIONS_IDENTIFIER = "(public|private|static|protected|abstract|native|synchronized) +([a-zA-Z0-9<>._?, ]+) +([a-zA-Z0-9_]+) *"
			+ "\\\\([a-zA-Z0-9<>\\\\[\\\\]._?, \\n]*\\\\) *([a-zA-Z0-9_ ,\\n]*) *\\\\{";
	// =======================================================================================//
	public static final String TRY_CATCT_IDENTIFIER = "(try|catch|Exception)";
}
