package formulaparser;

import java.util.ArrayList;

public class FormulaParser {
	
	private String formula;
	private ArrayList<String> parseResult;
	
	public FormulaParser(String formula) {
		this.formula = formula;
		this.parseResult = this.parse();
	}
	
	private ArrayList<String> parse() {
		ArrayList<String> parseList = new ArrayList<String>();
		StringBuffer token = new StringBuffer("");
		
		for(int i = 0; i < formula.length(); i++) {
			char currentChar = formula.charAt(i);
			if(currentChar == '=' || currentChar == ' ') continue;
			
			if(currentChar == '{') {
				if(token.toString().equals("")) {
					token.append(currentChar);
				} else {
					parseList.add(token.toString());
					token = new StringBuffer("{");
				}
			} else if(currentChar == '}') {
				token.append(currentChar);
				parseList.add(token.toString());
				token = new StringBuffer("");
			} else {
				token.append(currentChar);
			}
		}
		
		if(token.toString().equals("")) {
			return convertVar(parseList);
		} else {
			parseList.add(token.toString());
			return convertVar(parseList);
		}
	}
	
	private ArrayList<String> convertVar(ArrayList<String> parseList) {
		for(int i = 0; i < parseList.size(); i++) {
			String currentStr = parseList.get(i);
			if(currentStr.charAt(0) == '{' && currentStr.charAt(currentStr.length() - 1) == '}') {
				String var = currentStr.substring(1, currentStr.length() - 1);
				parseList.set(i, varToCell(var));
			}
		}
		
		return parseList;
	}
	
	private String varToCell(String var) {
		Boolean fixed = false;
		String newVar = "";
		
		for(int i = 0; i < var.length(); i++) {
			Character currentChar = var.charAt(i);
			if(currentChar == '$') {
				fixed = true;
				newVar += currentChar.toString();
			}
			if(Character.isAlphabetic(currentChar)) {
				fixed = false;
				newVar += currentChar.toString();
			}
			if(Character.isDigit(currentChar)) {
				if(fixed) {
					newVar += currentChar.toString();
				} else {
					newVar += "@";
				}
			}
		}
		
		return newVar;
	}
	
	public String toString() {
		StringBuffer str = new StringBuffer();
		for(int i = 0; i < parseResult.size(); i++) {
			str.append(parseResult.get(i));
		}
		
		return str.toString();
	}
	
}
