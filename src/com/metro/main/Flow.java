package com.metro.main;

import java.util.Scanner;

import com.metro.impl.Stations;
import com.metro.impl.XingStation;
import com.metro.interfacer.StationsI;

public class Flow {
	
	StationsI source;
	StationsI destination;
	int distance = 0;
	public Flow()
	{
		this.source = getStation("Source");
		this.destination = getStation("Destination");
	}
	public StationsI getStation(String type)
	{
		StationsI si;
		String code = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter "+ type +" Station Code:");
		code = sc.nextLine();
		sc.close();
		if(code.charAt(0) == 'X')
			si = new XingStation(code);
		else 
			si = new Stations(code);
		
		return si;	
	}
	public void totalDistance()
	{
		if(source.getStaionLine()==destination.getStaionLine())
		{
			distance += getDistance(source, destination);
		}
		else
		{
			//A to B = X2
			//A to C = X1
			//B to C = X3
			
			
		}
	}
	public int getDistance(StationsI source, StationsI destination)
	{
		return 0;
	}
	public double cost(Stations source, Stations destination)
	{
		double cost = 0;
		
		return cost;
		
	}
}
