package filedivider;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AverageFileWrite {
	
	private ArrayList<ArrayList<String>> inputList;
	private PrintWriter out;
	
	public AverageFileWrite(ArrayList<ArrayList<String>> list, ArrayList<String> nameList, String dir) throws Exception {
		inputList = list;
		out = new PrintWriter(new FileWriter(dir));
		
		for(int i = 0; i < nameList.size(); i++) {
			out.print(nameList.get(i) + "\t");
		}
		out.println();
		for(int i = 0; i < inputList.size(); i++) {
			for(int j = 0; j < inputList.get(i).size(); j++) {
				out.print(inputList.get(i).get(j) + "\t");
			}
			out.println();
		}
		
		out.flush();
		
		if(out != null)
			out.close();
	}

}
