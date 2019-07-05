	package divandequiv;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class FileWrite_EquivalentLoad {
	
	private PrintWriter out;
	private boolean bool;
	
	public FileWrite_EquivalentLoad(ArrayList<BigDecimal[]> list,int divide, double lambda, String dir) throws Exception {
		
		out = new PrintWriter(new FileWriter(dir));
		bool = false;
		
		out.println("구간\thi\tTi\tni\tLi\tfi\tfiTi^" + divide + "\thiniTi^" + divide);
		for(int i = 0; i < list.size(); i++) {
			if(i != (list.size() - 1)) {
				out.print((i + 1) + "\t");
			} else {
				out.print("합계\t");
			}
			
			for(int j = 0; j < list.get(i).length; j++) {
				if(list.get(i)[j] != null)
					out.print(list.get(i)[j].doubleValue() + "\t");
				else
					out.print("\t");
			}
			out.println();
		}
		
		//Te
		out.println("\t\t\t\t\t\tTe\t" + Math.pow(list.get(divide)[5].doubleValue(), 1/lambda));
		//Ne
		out.println("\t\t\t\t\t\tNe\t" + list.get(divide)[6].divide(list.get(divide)[5], 8, RoundingMode.HALF_UP).doubleValue());
		
		out.flush();
		
		if(out != null) {
			out.close();
		}
		
		bool = true;
		
	}
	
	public boolean isFinish() {
		return bool;
	}

}