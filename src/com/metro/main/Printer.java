package com.metro.main;

public class Printer {
	public void printTicker(String source, String destination, String distance, String cost) {
		System.out.println("*************************************************************");
		System.out.println("			Hyderabad Metro Rail");
		System.out.println("Source:		" + source + "		Distance ( stations): " + distance);
		System.out.println("Destination:	" + destination + "		Cost:	\u0930 " + cost + "/-");
		System.out.println("*************************************************************");
	}
}
