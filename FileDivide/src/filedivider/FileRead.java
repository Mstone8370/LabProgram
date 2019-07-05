package filedivider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileRead {
	
	private double[] rawDataArr;
	private double[] resultArr;
	
	private ArrayList<String> rawDataList;
	private Scanner s;
	
	public FileRead(String src) throws Exception {
		rawDataList = new ArrayList<String>();
		s = new Scanner(new BufferedReader(new FileReader(src)));
		
		while(s.hasNext()) {
			String str = s.nextLine();
			rawDataList.add(str);
		}
		
		if(s != null) {
			s.close();
		}
	}
	
	public ArrayList<String> getResult() {
		return rawDataList;
	}
	
}