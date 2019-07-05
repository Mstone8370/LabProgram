package divandequiv;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;

public class LoadCalculate {
	
	private ArrayList<String> titleList;
	private ArrayList<ArrayList<Double>> numberList;
	private int divide;
	private int numberOfAllData;
	private double lambda;
	private String str;
	
	//	Torque_F_L		Torque_F_R		Torque_R_L		Torque_R_R		RPM_F_L		RPM_F_R		RPM_R_L		RPM_R_R	
	private ArrayList<Double> Torque_R;
	private ArrayList<Double> Torque_L;
	private ArrayList<Double> RPM_R;
	private ArrayList<Double> RPM_L;
	
	private ArrayList<Double[]> processedList;	// 같은 시간의 토크와 RPM을 묶어서 토크 기준으로 정렬한 리스트.
	private ArrayList<BigDecimal[]> resultList;
	
	double[][] rangeArr;
	
	public LoadCalculate(ArrayList<String> title, ArrayList<ArrayList<Double>> number,int divide, double lambda, String str) throws Exception {
		titleList = title;
		numberList = number;
		this.divide = divide;
		numberOfAllData = numberList.size();
		this.lambda = lambda;
		this.str = str;
		
		processedList = new ArrayList<Double[]>();
		resultList = new ArrayList<BigDecimal[]>();
		
		rangeArr = new double[divide][3];	// From | To | Number of Data
		
		// F: front, R: rear
		if(!(str.equals("F") || str.equals("R"))) 
			throw new IllegalArgumentException("input error");
		
		init();
		
		divide();
		
		calc();
		
	}
	
	private void init() {	// 초기화
		if(str.equals("F")) {
			Torque_R = extract("Torque_F_R");
			Torque_L = extract("Torque_F_L");
			RPM_R = extract("RPM_F_R");
			RPM_L = extract("RPM_F_L");
		}
		if(str.equals("R")) {
			Torque_R = extract("Torque_R_R");
			Torque_L = extract("Torque_R_L");
			RPM_R = extract("RPM_R_R");
			RPM_L = extract("RPM_R_L");
		}
		
		mergeLists();	// 같은 시간의 토크와 RPM을 한 리스트로 합침. 토크는 왼쪽과 오른쪽 데이터를 더한 값, RPM은 평균값.
		
		Collections.sort(processedList, new java.util.Comparator<Double[]>() {	// 토크 기준으로 정렬
		    public int compare(Double[] a, Double[] b) {
		        return Double.compare(a[0], b[0]);
		    }
		});
	}
	
	private ArrayList<Double> extract(String title) {	// init()에 사용됨. 원하는 데이터만 가져옴.
		ArrayList<Double> tempList = new ArrayList<Double>();
		int index = titleList.indexOf(title);
		
		if(index < 0)
			return null;
		
		for(int i = 0; i < numberOfAllData; i++) {
			tempList.add(Math.abs(numberList.get(i).get(index)));
		}
		
		return tempList;
	}
	
	private void mergeLists() {	// init()에 사용됨. 여러 리스트를 한 리스트로 합침.
		for(int i = 0; i < numberOfAllData; i++) {
			Double[] tempArr = new Double[2];
			
			BigDecimal TorqueR = new BigDecimal(Torque_R.get(i).toString()), TorqueL = new BigDecimal(Torque_L.get(i).toString());
			BigDecimal RPMR  = new BigDecimal(RPM_R.get(i).toString()), RPML = new BigDecimal(RPM_L.get(i).toString());
			
			tempArr[0] = (TorqueR.add(TorqueL)).doubleValue();
			tempArr[1] = ((RPMR.add(RPML)).divide(new BigDecimal("2"))).doubleValue();
			
			processedList.add(tempArr);
		}
	}
	
	private void divide() {	// 등분
		BigDecimal max = new BigDecimal(processedList.get(processedList.size() - 1)[0]);
		BigDecimal min = new BigDecimal(processedList.get(0)[0]);
		BigDecimal range = (max.subtract(min)).divide(new BigDecimal(divide), 2, RoundingMode.UP);
		BigDecimal temp = new BigDecimal(min.doubleValue());
		
		rangeArr[0][0] = min.doubleValue();
		rangeArr[0][1] = (temp = temp.add(range)).doubleValue();
		for(int i = 1; i < divide; i++) {
			rangeArr[i][0] = rangeArr[i - 1][1];
			rangeArr[i][1] = (temp = temp.add(range)).doubleValue();
		}
		
		for(int i = 0; i < numberOfAllData; i++) {
			for(int j = 0; j < divide; j++) {
				if(rangeArr[j][0] <= processedList.get(i)[0] && processedList.get(i)[0] < rangeArr[j][1]) {
					rangeArr[j][2] += 1;
					break;
				}
			}
		}
	}
	
	private void calc() {
		BigDecimal hi_sum = new BigDecimal(0);
		BigDecimal Li_sum = new BigDecimal(0);
		BigDecimal fi_sum = new BigDecimal(0);
		BigDecimal fiTi_sum = new BigDecimal(0);
		BigDecimal hiniTi_sum = new BigDecimal(0);
		
		int scale = 8;
		
		for(int i = 0; i < divide; i++) {
			BigDecimal[] tempArr = new BigDecimal[7];	// hi | Ti | ni | Li | fi | fiTi^divide | hiniTi^divide
			BigDecimal hi, Ti, ni, Li, hiniTi;
			int previousNumberOfData = 0;
			
			for(int j = 0; j < i; j++) {
				previousNumberOfData += rangeArr[j][2];
			}
			
			// hi
			hi = new BigDecimal(rangeArr[i][2]).divide(new BigDecimal(numberOfAllData), scale, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
			tempArr[0] = hi;
			hi_sum = hi_sum.add(hi);
			
			// Ti, ni
			BigDecimal T_sum = new BigDecimal(0);
			BigDecimal n_sum = new BigDecimal(0);
			for(int j = previousNumberOfData; j < (previousNumberOfData + rangeArr[i][2]); j++) {
				T_sum = T_sum.add(new BigDecimal(processedList.get(j)[0]));
				n_sum = n_sum.add(new BigDecimal(processedList.get(j)[1]));
			}
			//Ti
			Ti = T_sum.divide(new BigDecimal(rangeArr[i][2]), scale, RoundingMode.HALF_UP);
			tempArr[1] = Ti;
			//ni
			ni = n_sum.divide(new BigDecimal(rangeArr[i][2]), scale, RoundingMode.HALF_UP);
			tempArr[2] = ni;
			
			//Li
			Li = ni.multiply(hi).divide(new BigDecimal(100), scale, RoundingMode.HALF_UP);
			tempArr[3] = Li;
			Li_sum = Li_sum.add(Li);
			
			//hiniTi^gamma
			//hiniTi = hi.multiply(ni).multiply(Ti.pow(divide)).divide(new BigDecimal(100), scale, RoundingMode.HALF_UP);
			hiniTi = hi.multiply(ni).multiply(new BigDecimal(Math.pow(Ti.doubleValue(), lambda))).divide(new BigDecimal(100), scale, RoundingMode.HALF_UP);
			tempArr[6] = hiniTi;
			hiniTi_sum = hiniTi_sum.add(hiniTi);
			
			resultList.add(tempArr);
		}
		
		BigDecimal[] tempArr2 = new BigDecimal[7];
		tempArr2[0] = hi_sum;
		tempArr2[3] = Li_sum;
		tempArr2[6] = hiniTi_sum;
		
		resultList.add(tempArr2);
		
		for(int i = 0; i < divide; i++) {
			BigDecimal Ti, Li, fi, fiTi;
			
			//fi
			Li = resultList.get(i)[3];
			fi = Li.divide(Li_sum, 8, RoundingMode.HALF_UP);
			resultList.get(i)[4] = fi;
			fi_sum = fi_sum.add(fi);
			
			//fiTi^gamma
			Ti = resultList.get(i)[1];
			//fiTi = fi.multiply(Ti.pow(divide));
			fiTi = fi.multiply(new BigDecimal(Math.pow(Ti.doubleValue(), lambda)));
			resultList.get(i)[5] = fiTi;
			fiTi_sum = fiTi_sum.add(fiTi);
		}
		
		resultList.get(divide)[4] = fi_sum;
		resultList.get(divide)[5] = fiTi_sum;
	}
	
	public ArrayList<BigDecimal[]> getResult(){
		return resultList;
	}
	
	public ArrayList<Double[]> getProcessedList() {
		return processedList;
	}
	
	private void printList(ArrayList list, String str) {
		System.out.println(str + "\n");
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
