package calccycle;

public class Formula {

	private String formula;
	private String format;
	
	public Formula(String formula, String format) {
		this.formula = formula;
		this.format = format;
	}
	
	public Formula(String formula) {
		this.formula = formula;
		this.format = "0.00";
	}
	
	public String getFormulaFormat() {
		return this.format;
	}
	
	public String toString() {
		return this.formula;
	}
	
}
