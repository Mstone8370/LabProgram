package calccycle;

import java.util.ArrayList;

import org.apache.poi.ss.util.CellReference;

import formulaparser.FormulaParser;

public class Calc {
	
	private ArrayList<ArrayList> dataList;
	private Double diameter;
	private String formula;
	private final Double PI = Math.PI;
	
//	public Calc(ArrayList<ArrayList> list, Double diameter) {
//		this.dataList = list;
//		this.diameter = diameter;
//		
//		calc();
//	}
	
	public Calc(ArrayList<ArrayList> list, Double diameter, String formula) {
		this.dataList = list;
		this.diameter = diameter;
		this.formula = new FormulaParser(formula).toString();
		
		calc();
	}
	
	private void calc() {
		
		int cycleIndex = 0;
		int torqueIndex = 1;
		int stressIndex = 2;
		int lifeIndex = 3;
		int damageIndex = 4;
		
		int startIndex = 1, lastIndex = 1;
		
		for(int i = 0; i < dataList.size(); i++) {
			String currentFormula = formula.replaceAll("@", (i + 1) + "");		//
			
			ArrayList currentList = dataList.get(i);
			if(currentList.isEmpty()) {
				dataList.remove(i);
				continue;
			}
			if(currentList.get(0) instanceof String) {
				currentList.add("Stress (MPa)");
				currentList.add("Fatigue life (cycles)");
				currentList.add("Damage");
				startIndex = i + 2;
				continue;
			}
			lastIndex = i + 1;
			
			String cycleCell = CellReference.convertNumToColString(cycleIndex) + (i + 1);
			String torqueCell = CellReference.convertNumToColString(torqueIndex) + (i + 1);
			String stressCell = CellReference.convertNumToColString(stressIndex) + (i + 1);
			String lifeCell = CellReference.convertNumToColString(lifeIndex) + (i + 1);
			
			Formula stressFormula = new Formula("(16*" + torqueCell + "*1000)/(PI()*" + diameter + "^3)", "0.0000");
			//Formula lifeFormula = new Formula("10^(6-6.097*LOG(" + stressCell + "/223))", "0");
			Formula lifeFormula = new Formula(currentFormula, "0");
			Formula damageFormula = new Formula(cycleCell + "/" + lifeCell, "0.00000000000000000");
			
			currentList.add(stressFormula);
			currentList.add(lifeFormula);
			currentList.add(damageFormula);
		}
		
		ArrayList lastList = new ArrayList();
		String startCell = CellReference.convertNumToColString(damageIndex) + startIndex;
		String lastCell = CellReference.convertNumToColString(damageIndex) + lastIndex;
		Formula damageSum = new Formula("SUM(" + startCell + ":" + lastCell + ")", "0.00000000000000000");
		
		lastList.add(new String(""));
		lastList.add(new String(""));
		lastList.add(new String(""));
		lastList.add(new String("Damage sum"));
		lastList.add(damageSum);
		
		dataList.add(lastList);
	}
	
	public ArrayList<ArrayList> getResult() {
		return dataList;
	}

}
