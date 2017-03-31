package com.metro.test;

import com.metro.main.Flow;

public class Runner {

	public static void main(String[] args) {
		//new Flow();
		new Flow("A4", "B12");
		new Flow("A1", "A2");
		new Flow("A1", "B1");
		new Flow("A1", "C1");
		new Flow("B1", "C1");
		new Flow("A1", "X1");
		new Flow("B1", "X1");
		new Flow("C1", "X1");
		new Flow("X1", "X2");
	}

}
