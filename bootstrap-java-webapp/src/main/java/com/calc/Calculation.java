package com.calc;

public class Calculation {
	
	public static int findMax(int values[]) {
		int max = values[0];
		
		for (int i = 1; i < values.length; i++) 
			if (max<values[i]) {
				max = values[i];
			}
		
		return max;		
	}
}
