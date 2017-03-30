package com.metro.main;

import java.util.List;
import java.util.Scanner;

import com.metro.impl.Station;
import com.metro.impl.XingStation;
import com.metro.interfacer.StationI;
import com.metro.utils.PropertyHelper;

public class Flow {
	
	StationI source;
	StationI destination;
	int distance = 0;
	public Flow()
	{
		this.source = getStation("Source");
		this.destination = getStation("Destination");
	}
	public StationI getStation(String type)
	{
		StationI si;
		String code = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter "+ type +" Station Code:");
		code = sc.nextLine();
		sc.close();
		if(code.charAt(0) == 'X')
			si = new XingStation(code);
		else 
			si = new Station(code);
		
		return si;	
	}
	public void totalDistance()
	{
		if((source.getStaionLine()==destination.getStaionLine()) && 
				(source.getStaionLine()!='X') || (destination.getStaionLine()!='X'))
		{
			distance += getDistance(source, destination);
		}
		else if((source.getStaionLine()!='X') || (destination.getStaionLine()!='X'))
		{
			//A to B = X2
			//A to C = X1
			//B to C = X3
			String via = PropertyHelper.getConnection(source.getStaionLine(), destination.getStaionLine());
			List<String> viaResolvedNames = PropertyHelper.getSeduName(via);
			String viaResolvedDes = "";
			String viaResolvedSrc = "";
			if(viaResolvedNames.get(0).indexOf(source.getStaionLine())==0)
			{
				viaResolvedDes= viaResolvedNames.get(0);
				viaResolvedSrc= viaResolvedNames.get(1);
			} else {
				viaResolvedDes= viaResolvedNames.get(1);
				viaResolvedSrc= viaResolvedNames.get(0);
			}
			StationI vrd = new Station(viaResolvedDes);
			StationI vrs = new Station(viaResolvedSrc);
			distance += getDistance(source, vrd);
			distance +=getDistance(vrs, destination);
			
		}
	}
	public int getDistance(StationI source, StationI destination)
	{
		return 0;
	}
	public double cost(Station source, Station destination)
	{
		double cost = 0;
		
		return cost;
		
	}
}
