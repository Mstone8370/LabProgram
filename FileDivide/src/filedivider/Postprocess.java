package filedivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Postprocess {

	private ArrayList<String> inputList;
	private ArrayList<String> nameList;
	private ArrayList<Double> timeList;
	private ArrayList<String> titleList;
	private ArrayList<ArrayList<Double>> numberList;
	
	public Postprocess(ArrayList<String> list) {
		inputList = list;
		nameList = new ArrayList<String>();
		timeList = new ArrayList<Double>();
		titleList = new ArrayList<String>();
		numberList = new ArrayList<ArrayList<Double>>();
		
		init();
		
		//nameToTitle();
		timeToNumber();
	}
	
	private void init() {
		for(int i = 0; i < inputList.size(); i++) {
			String tempStr = inputList.get(i);
			StringTokenizer parser = new StringTokenizer(tempStr, "	");
			try	{
				timeList.add(Double.valueOf(parser.nextToken()));
			} catch(Exception e) {
				timeList.add(-1.0);
				nameList.add(tempStr);
			}
		}
	}
	
	public ArrayList<String> getTitle() {
		return titleList;
	}
	
	public ArrayList<ArrayList<Double>> getNumber() {
		return numberList;
	}
	
	private void nameToTitle() {
		String str = nameList.get(0);
		String[] arr = str.split("	");
		ArrayList<String> tempList = new ArrayList<String>(Arrays.asList(arr));
		
		titleList = tempList;
	}
	
	private void timeToNumber() {
		for(int i = 0; i < timeList.size(); i++) {
			if(!timeList.get(i).equals(Double.valueOf(-1))) {
				String str = inputList.get(i);
				String[] arr = str.split("	");
				ArrayList<Double> tempList = new ArrayList<Double>();
				for(int j = 0; j < arr.length; j++) {
					try {
						tempList.add(Double.valueOf(arr[j]));
					} catch(Exception e) {
						tempList.add(Double.valueOf(0));
					}
				}
				numberList.add(tempList);
			}
		}
	}
	
}
