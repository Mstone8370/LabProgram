package calc;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class FileWrite {
	
	private PrintWriter out;
	private boolean bool;
	
	public FileWrite(ArrayList<ArrayList<Double>> list, String dir) throws Exception {
		
		out = new PrintWriter(new FileWriter(dir));
		bool = false;
		
		//맨 위 출력
		out.println("Tillage depth\tActual Engine Brake Torque\tTorque_F_L\tTorque_F_R\tTorque_R_L\tTorque_R_R\tTorque_PTO\tSlip ratio_F_L\tSlip ratio_F_R\tSlip ratio_R_L\tSlip ratio_R_R\tEngine Power\tPower_F_L\tPower_F_R\tPower_R_L\tPower_R_R\tPower_PTO\tAxle Power\tMain pressure Power\tSub pressure Power\tTraction\tTraction Power\tTraction coefficient\tTraction efficiency"); 
		
		//내용 출력
		out.println();
		for(int j = 0; j < list.get(0).size(); j++) {
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).get(j) == null)
					out.print("#DIV/0!\t");
				else
					out.print(list.get(i).get(j) + "\t");
			}
			out.println();
		}
		
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