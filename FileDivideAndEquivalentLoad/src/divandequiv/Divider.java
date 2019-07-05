package divandequiv;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Divider {
	
	private ArrayList<String> inputList;
	private ArrayList<String> resultList;
	private ArrayList<String> savedDir;
	private ArrayList<String> nameList;
	private ArrayList<Double> timeList;
	private double from;
	private double to;
	private int divide;
	private String dir;
	private boolean bool;
	
	private FileWrite_FileDivide w;
	
	public Divider(ArrayList<String> list, double from, double to, int divide, String dir) {
		inputList = list;
		nameList = new ArrayList<String>();
		timeList = new ArrayList<Double>();
		savedDir = new ArrayList<String>();
		this.from = from;
		this.to = to;
		this.divide = divide;
		this.dir = dir;
		bool = false;
		
		init();
		
		if(divide < 2) {
			notDivide();
		} else {
			divide();
		}
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
	
	private ArrayList<String> cut(double from, double to) {
		ArrayList<String> cuttedList = (ArrayList<String>) inputList.clone();
		int fromIndex = 0, toIndex = inputList.size() - 1;
		
		Iterator iterator = timeList.iterator();
		while(iterator.hasNext()) {
			Double next = (Double) iterator.next();
			if(next <= from)
				fromIndex = timeList.indexOf(next);
			if(next <= to)
				toIndex = timeList.indexOf(next);
		}
		//System.out.println("fromIndex = " + fromIndex + "\t toIndex = " + toIndex);
		
		try {
			for(int i = 0; i < inputList.size() - toIndex - 1; i++) {
				cuttedList.remove(cuttedList.size() - 1);
			}
			for(int i = 0; i < fromIndex; i++) {
				cuttedList.remove(0);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return cuttedList;
	}
	
	private void divide() {
		double range = (Math.abs(to * 100 - from * 100) + 1) / divide / 100;
		//System.out.println(range);
		
		double[][] rangeArr = new double[divide][2];
		
		for(int i = 0; i < divide; i++) {
			if(i == 0) {
				rangeArr[i][0] = from;
			} else {
				rangeArr[i][0] = rangeArr[i - 1][1] + 0.01;
			}
			rangeArr[i][1] = (rangeArr[i][0] * 100 + range * 100 - 1) / 100;
		}
		
		rangeArr[rangeArr.length - 1][1] = to;
		
		for(int i = 0; i < divide; i++) {
			try {
				w = new FileWrite_FileDivide(this.getName(), cut(rangeArr[i][0], rangeArr[i][1]), dir + "\\Divide_result_" + (i + 1) + ".txt");
				savedDir.add(dir + "\\Divide_result_" + (i + 1) + ".txt");
				bool = w.isFinish();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void notDivide() {
		
		try {
			w = new FileWrite_FileDivide(this.getName(), cut(from, to), dir + "\\Divide_result.txt");
			savedDir.add(dir + "\\Divide_result.txt");
			bool = w.isFinish();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean isFinish() {
		return bool;
	}
	
	public ArrayList<String> getSavedDir(){
		return savedDir;
	}
	
	public ArrayList<String> getResult() {
		return resultList;
	}
	
	public ArrayList<String> getName() {
		return nameList;
	}

}
