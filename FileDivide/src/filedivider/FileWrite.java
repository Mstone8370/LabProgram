package filedivider;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class FileWrite {
	
	private ArrayList<String> inputList;
	private PrintWriter out;
	private boolean bool;
	
	public FileWrite(ArrayList<String> name, ArrayList<String> list, String dir) throws Exception {
		/*
		System.out.println("\n------------------------------");
		for(int i = 0; i < name.size(); i++) {
			System.out.println(name.get(i));
		}
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		*/
		
		inputList = list;
		out = new PrintWriter(new FileWriter(dir));
		bool = false;
		
		for(int i = 0; i < name.size(); i++) {
			out.println(name.get(i));
		}
		for(int i = 0; i < list.size(); i++) {
			out.println(list.get(i));
		}
		
		printAverage();
		
		out.flush();
		
		if(out != null) {
			out.close();
		}
		
		bool = true;
		
	}
	
	public boolean isFinish() {
		return bool;
	}
	
	private void printAverage() {
		ArrayList<String> list = calcAverage();
		
		out.println();
		out.print("Average\t");
		for(int i = 1; i < list.size(); i++) {
			out.print(list.get(i) + "\t");
		}
	}
	
	public ArrayList<String> calcAverage() {
		Postprocess p = new Postprocess(inputList);
		ArrayList<ArrayList<Double>> list = p.getNumber();
		ArrayList<String> averageList = new ArrayList<String>();
		
		for(int j = 0; j < list.get(0).size(); j++) {
			BigDecimal temp = BigDecimal.ZERO;
			for(int i = 0; i < list.size(); i++) {
				temp = temp.add(new BigDecimal(list.get(i).get(j)));
			}
			temp = temp.divide(new BigDecimal(list.size()), 8, RoundingMode.UP);
			averageList.add((Double.valueOf(temp.doubleValue())).toString());
		}
		
		return averageList;
	}

}