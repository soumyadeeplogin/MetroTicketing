package com.metro.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
	double cost = 10;
	
	
	
	public Flow()
	{
		this.source = getStation("Source");
		this.destination = getStation("Destination");
		//sc.close();
		compute();
	}
	
	public void compute(){
		totalDistanceAndCost();
		new Printer().printTicker(source.getStationName(), destination.getStationName(), String.valueOf(distance), Double.toString(cost));
	}
	
	final private static StationI getStation(String type)
	{
		StationI si;
		String code = null;
		System.out.println("Enter "+ type +" Station Code:");
		Scanner sc = new Scanner(System.in);
		
		if(sc.hasNext()){
			code = sc.next();
		}	
		if(code.charAt(0) == 'X')
			si = new XingStation(code);
		else 
			si = new Station(code);		

		return si;	
	}
	public void totalDistanceAndCost()
	{
		if((source.getStaionLine()==destination.getStaionLine()) && 
				((source.getStaionLine()!='X') || (destination.getStaionLine()!='X')))
		{
			distance += getDistance(source, destination);
			cost += calCost(source, distance-3);
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
			int tempDistance =0;
			distance += getDistance(source, vrd);
			tempDistance = distance;
			cost += calCost(source, distance-3);
			distance +=getDistance(vrs, destination);
			cost += calCost(vrs, distance-tempDistance);
		}
		else if(((source.getStaionLine()!='X') && (destination.getStaionLine()=='X')) ||
				
				((source.getStaionLine()=='X') && (destination.getStaionLine()!='X'))){
			StationI X = null;
			StationI nonX = null;
			if(source.isInterchangeStation()){
				//source is X
				X = source;
				nonX = destination;
			} else {
				//destination is X
				X = destination;
				nonX = source;
			}
			
			boolean directToX = false;
			
			
			
			
		} else if((source.getStaionLine()=='X') && (destination.getStaionLine()!='X')) {
			
		} else {
			System.out.println("Critical Case");
		}
		
		//AtoX
		//XtoX
	}
	public int getDistance(StationI source, StationI destination)
	{
		int sourceValue = Integer.parseInt(source.getStationCode().substring(1));
		int destinationValue = Integer.parseInt(destination.getStationCode().substring(1));
		return Math.abs(sourceValue-destinationValue);
	}
	public double calCost(StationI source, int distance)
	{
		switch (source.getStaionLine())
		{
		case 'A':
			return distance*2.5;
		case 'B':
			return distance*2.0;
		case 'c':
			return distance*3.0;
		default:
			return 0.0;
		}
	}
}
