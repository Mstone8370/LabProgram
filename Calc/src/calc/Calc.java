package calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Calc {
	
	private ArrayList<String> titleList;
	private ArrayList<ArrayList<Double>> numberList;
	private ArrayList<BigDecimal> AEBT, ES, L1, L2, L3, POH, MP, R_FL, R_FR, R_P, R_RL, R_RR, S, SP, T_FL, T_FR, T_P, T_RL, T_RR;
	private ArrayList<BigDecimal> TD, SR_FL, SR_FR, SR_RL, SR_RR, EP, P_FL, P_FR, P_RL, P_RR, P_P,  AP, MPP, SPP, T, TP, TC, TE, AS, AMD_FL, AMD_FR, AMD_RL, AMD_RR;
	private ArrayList<ArrayList<BigDecimal>> calcResult;
	private ArrayList<ArrayList<Double>> resultList;
	
	private final int dataLength;
	private BigDecimal mainP, mainP_E, subP, subP_E, load, frontD, rearD;
	
	private static BigDecimal PI2 = new BigDecimal(Math.PI).multiply(new BigDecimal(2));
	
	public Calc(ArrayList<String> title, ArrayList<ArrayList<Double>> number, double mainP, double mainP_E , double subP, double subP_E, double load, double frontD, double rearD) {
		titleList = title;
		numberList = number;
		dataLength = numberList.size();
		this.mainP = new BigDecimal(mainP);
		this.mainP_E = new BigDecimal(mainP_E);
		this.subP = new BigDecimal(subP);
		this.subP_E = new BigDecimal(subP_E);
		this.load = new BigDecimal(load);
		this.frontD = new BigDecimal(frontD);
		this.rearD = new BigDecimal(rearD);
		
		init();
		
		calcResult = calc();
		resultList = toDouble(calcResult);
	}
	
	private void init() {
		AEBT = extract("Actual Engine Brake Torque", false);
		ES = extract("Engine Speed", false);
		L1 = extract("Load1", false);
		L2 = extract("Load2", false);
		L3 = extract("Load3", false);
		POH = extract("position_of_hitch", false);
		MP = extract("Main_pressure", false);
		R_FL = extract("RPM_F_L", false);
		R_FR = extract("RPM_F_R", false);
		R_P = extract("RPM_PTO", false);
		R_RL = extract("RPM_R_L", false);
		R_RR = extract("RPM_R_R", false);
		S = extract("Speed_kmh", false);
		SP = extract("Sub_Pressure", false);
		T_FL = extract("Torque_F_L", true);
		T_FR = extract("Torque_F_R", true);
		T_P = extract("Torque_PTO", true);
		T_RL = extract("Torque_R_L", true);
		T_RR = extract("Torque_R_R", true);

		TD = new ArrayList<BigDecimal>();		// Tillage depth
		SR_FL = new ArrayList<BigDecimal>();	// Slip ratio_F_L
		SR_FR = new ArrayList<BigDecimal>();	// Slip ratio_F_R
		SR_RL = new ArrayList<BigDecimal>();	// Slip ratio_R_L
		SR_RR = new ArrayList<BigDecimal>();	// Slip ratio_R_R
		EP = new ArrayList<BigDecimal>();		// Engine Power
		P_FL = new ArrayList<BigDecimal>();		// Power_F_L
		P_FR = new ArrayList<BigDecimal>();		// Power_F_R
		P_RL = new ArrayList<BigDecimal>();		// Power_R_L
		P_RR = new ArrayList<BigDecimal>();		// Power_R_R
		P_P = new ArrayList<BigDecimal>();		// Power_PTO
		AP = new ArrayList<BigDecimal>();		// Axle Power
		MPP = new ArrayList<BigDecimal>();		// Main pressure Power
		SPP = new ArrayList<BigDecimal>();		// Sub pressure Power
		T = new ArrayList<BigDecimal>();		// Traction
		TP = new ArrayList<BigDecimal>();		// Traction Power
		TC = new ArrayList<BigDecimal>();		// Traction coefficient
		TE = new ArrayList<BigDecimal>();		// Traction efficiency
		AS = new ArrayList<BigDecimal>();		// Actual speed
		AMD_FL = new ArrayList<BigDecimal>();	// Actual Moving Distance F_L
		AMD_FR = new ArrayList<BigDecimal>();	// Actual Moving Distance F_R
		AMD_RL = new ArrayList<BigDecimal>();	// Actual Moving Distance R_L
		AMD_RR = new ArrayList<BigDecimal>();	// Actual Moving Distance R_R
	}
	
	private ArrayList<BigDecimal> extract(String title, boolean abs) {	// init()에 사용됨. 원하는 데이터만 가져옴.
		ArrayList<BigDecimal> extractedList = new ArrayList<BigDecimal>();
		int index = titleList.indexOf(title);
		
		if(index < 0)
			return null;
		
		for(int i = 0; i < dataLength; i++) {
			if(abs)
				extractedList.add(new BigDecimal(Math.abs(numberList.get(i).get(index))));
			else
				extractedList.add(new BigDecimal(numberList.get(i).get(index)));
		}
		
		return extractedList;
	}

	private ArrayList<ArrayList<BigDecimal>> calc() {
		final int scale = 15;
		final BigDecimal traction = ((L1.get(0)).add(L2.get(0)).add(L3.get(0))).multiply(new BigDecimal(9.8)).divide(new BigDecimal(1000), scale, RoundingMode.HALF_UP);
		
		ArrayList<ArrayList<BigDecimal>> result = new ArrayList<ArrayList<BigDecimal>>();
		
		for(int i = 0; i < dataLength; i++) {
			AS.add(S.get(i).multiply(new BigDecimal(1000)).divide(new BigDecimal(60), scale, RoundingMode.HALF_UP));					// Actual speed
			AMD_FL.add((R_FL.get(i)).multiply(PI2).multiply(frontD));																	// Actual Moving Distance F_L
			AMD_FR.add((R_FR.get(i)).multiply(PI2).multiply(frontD));																	// Actual Moving Distance F_R
			AMD_RL.add((R_RL.get(i)).multiply(PI2).multiply(rearD));																	// Actual Moving Distance R_L
			AMD_RR.add((R_RR.get(i)).multiply(PI2).multiply(rearD));																	// Actual Moving Distance R_R
			
			//
			TD.add(POH.get(i).multiply(new BigDecimal(-0.23)).add(new BigDecimal(33.774)));												// Tillage depth
			try {
				SR_FL.add(new BigDecimal(1).subtract(AS.get(i).divide(AMD_FL.get(i), scale, RoundingMode.HALF_UP)));						// Slip ratio_F_L			div0
			} catch(ArithmeticException e) { SR_FL.add(null); }
			try {
				SR_FR.add(new BigDecimal(1).subtract(AS.get(i).divide(AMD_FR.get(i), scale, RoundingMode.HALF_UP)));						// Slip ratio_F_R			div0
			} catch(ArithmeticException e) { SR_FR.add(null); }
			try {
				SR_RL.add(new BigDecimal(1).subtract(AS.get(i).divide(AMD_RL.get(i), scale, RoundingMode.HALF_UP)));						// Slip ratio_R_L			div0
			} catch(ArithmeticException e) { SR_RL.add(null); }
			try {
				SR_RR.add(new BigDecimal(1).subtract(AS.get(i).divide(AMD_RR.get(i), scale, RoundingMode.HALF_UP)));						// Slip ratio_R_R			div0
			} catch(ArithmeticException e) { SR_RR.add(null); }
			EP.add((PI2.multiply(AEBT.get(i)).multiply(ES.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));			// Engine Power
			P_FL.add((PI2.multiply(T_FL.get(i)).multiply(R_FL.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));		// Power_F_L
			P_FR.add((PI2.multiply(T_FR.get(i)).multiply(R_FR.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));		// Power_F_R
			P_RL.add((PI2.multiply(T_RL.get(i)).multiply(R_RL.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));		// Power_R_L
			P_RR.add((PI2.multiply(T_RR.get(i)).multiply(R_RR.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));		// Power_R_R
			P_P.add((PI2.multiply(T_P.get(i)).multiply(R_P.get(i))).divide(new BigDecimal(60000), scale, RoundingMode.HALF_UP));		// Power_PTO
			AP.add(P_FL.get(i).add(P_FR.get(i)).add(P_RL.get(i)).add(P_RR.get(i)));														// Axle Power
																																		// Main pressure Power
			MPP.add((mainP.multiply(ES.get(i)).multiply(mainP_E).divide(new BigDecimal(1000), scale, RoundingMode.HALF_UP)).multiply(MP.get(i)).multiply(new BigDecimal(0.1)).divide(new BigDecimal(60), scale, RoundingMode.HALF_UP));
																																		// Sub pressure Power
			SPP.add((subP.multiply(ES.get(i)).multiply(subP_E).divide(new BigDecimal(1000), scale, RoundingMode.HALF_UP)).multiply(SP.get(i)).multiply(new BigDecimal(0.1)).divide(new BigDecimal(60), scale, RoundingMode.HALF_UP));
																																		// Traction
			T.add((((L1.get(i)).add(L2.get(i)).add(L3.get(i))).multiply(new BigDecimal(9.8)).divide(new BigDecimal(1000), scale, RoundingMode.HALF_UP)).subtract(traction));
			TP.add((T.get(i)).multiply(S.get(i)).divide(new BigDecimal(3.6), scale, RoundingMode.HALF_UP));								// Traction Power
			try {																														// Traction coefficient		div0
				TC.add(((T.get(i)).multiply(new BigDecimal(1000))).divide((load.multiply(new BigDecimal(9.8))), scale, RoundingMode.HALF_UP));
			} catch(ArithmeticException e) { TC.add(null); }
			try {
				TE.add((TP.get(i)).divide((AP.get(i)), scale, RoundingMode.HALF_UP));														// Traction efficiency		div0
			} catch(ArithmeticException e) { TE.add(null); }
			
		}
		
		result.add(TD);
		result.add(AEBT);
		result.add(T_FL);
		result.add(T_FR);
		result.add(T_RL);
		result.add(T_RR);
		result.add(T_P);
		result.add(SR_FL);
		result.add(SR_FR);
		result.add(SR_RL);
		result.add(SR_RR);
		result.add(EP);
		result.add(P_FL);
		result.add(P_FR);
		result.add(P_RL);
		result.add(P_RR);
		result.add(P_P);
		result.add(AP);
		result.add(MPP);
		result.add(SPP);
		result.add(T);
		result.add(TP);
		result.add(TC);
		result.add(TE);
		
		return result;
	}
	
	private ArrayList<ArrayList<Double>> toDouble(ArrayList<ArrayList<BigDecimal>> list){
		ArrayList<ArrayList<Double>> convertedList = new ArrayList<ArrayList<Double>>();
		
		for(int i = 0; i < list.size(); i++) {
			ArrayList<Double> tempList = new ArrayList<Double>();
			for(int j = 0; j < list.get(i).size(); j++) {
				if(list.get(i).get(j) == null)
					tempList.add(null);
				else
					tempList.add(list.get(i).get(j).doubleValue());
			}
			convertedList.add(tempList);
		}
		
		return convertedList;
	}
	
	public ArrayList<ArrayList<Double>> getResult() {
		return resultList;
	}

}
