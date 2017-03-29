package com.metro.main;

import java.util.Scanner;

import com.metro.impl.Stations;

public class Flow {
	
	Stations source;
	Stations destination;
	int distance = 0;
	public Flow()
	{
		this.source = getStation("Source");
		this.destination = getStation("Destination");
	}
	public Stations getStation(String type)
	{
		String code = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter "+ type +" Station Code:");
		code = sc.nextLine();
		sc.close();
		return new Stations(code);	
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
	public int getDistance(Stations source, Stations destination)
	{
		return 0;
	}
	public double cost(Stations source, Stations destination)
	{
		double cost = 0;
		
		return cost;
		
	}
}
