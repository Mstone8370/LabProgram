package formulaparser;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "= {A2}/{D2}";
		FormulaParser parser = new FormulaParser(str);
		
		String formula = parser.toString();
		System.out.println(formula);
		
		String temp = formula;
		temp = formula.replaceAll("@", "{i}");
		System.out.println(temp);
		System.out.println(formula);
	}

}
