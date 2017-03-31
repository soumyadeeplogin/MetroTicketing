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
import com.metro.main.CalculateCost;

public class Flow {
	
	StationI source;
	StationI destination;
	int distance = 0;
	double cost = 10;
	CalculateCost cc = null;
	
	
	public Flow()
	{
		this.source = getStation("Source");
		this.destination = getStation("Destination");
		//sc.close();
		cc = new CalculateCost();
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
	
	public int lineChecker()
	{
		//Line same but not Xing
		if((source.getStationLine()==destination.getStationLine()) 
				&& ((source.getStationLine()!='X') || (destination.getStationLine()!='X')))
		{
			return 1;
		} 
		//Line different not Xing
		else if((source.getStationLine()!='X') && (destination.getStationLine()!='X'))
		{
			return 2;
		} 
		//Line to Xing
		else if(((source.getStationLine()!='X') && (destination.getStationLine()=='X')) 
				|| ((source.getStationLine()=='X') && (destination.getStationLine()!='X')))
		{
			return 3;
		}
		//Xing to Xing
		else if((source.getStationLine()=='X') && (destination.getStationLine()=='X')) 
		{
			return 4;
		}
		else 
			return 5;
	}
	public void totalDistanceAndCost()
	{
		if(lineChecker()==1)
		{
			sameLineTravel(this.source,this.destination);
		}
		else if(lineChecker()==2)
		{
			crossInterchange(source, destination);
		}
		else if(lineChecker()==3)
		{
			
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
			
			if((X.getSudoName(0).getStationLine()==nonX.getStationLine()) || (X.getSudoName(1).getStationLine()==nonX.getStationLine()))
			{
				StationI xDest = ((X.getSudoName(0).getStationLine()==nonX.getStationLine())?X.getSudoName(0):X.getSudoName(1));
				distance += getDistance(nonX, xDest);
				if(distance>3){
					cost += cc.calCost(nonX, distance-3);
				}
				else {
					cost = 10;
				}
			} 
			else
			{
				StationI xDest = ((X.getSudoName(0).getStationLine()==nonX.getStationLine())?X.getSudoName(0):X.getSudoName(1));
				crossInterchange(source, xDest);
			}			
		} 
		else if(lineChecker()==4) {
			StationI src = null;
			StationI dst = null;
			if(source.getSudoName(0).getStationLine()==destination.getSudoName(0).getStationLine()){
				src = source.getSudoName(0);
				dst = destination.getSudoName(0);
			} else if(source.getSudoName(0).getStationLine()==destination.getSudoName(1).getStationLine()){
				src = source.getSudoName(0);
				dst = destination.getSudoName(1);
			} else if(source.getSudoName(1).getStationLine()==destination.getSudoName(0).getStationLine()){
				src = source.getSudoName(1);
				dst = destination.getSudoName(0);
			} else if(source.getSudoName(1).getStationLine()==destination.getSudoName(1).getStationLine()){
				src = source.getSudoName(1);
				dst = destination.getSudoName(1);
			}

			sameLineTravel(src,dst);
			
		} else {
			System.out.println("Critical Case");
		}
	}
	
	public void sameLineTravel(StationI source, StationI destination) {
		distance += getDistance(source, destination);
		if(distance>3){
			cost += cc.calCost(source, distance-3);
		} else {
			cost = 10;
		}
	}
	
	public void crossInterchange(StationI source, StationI destination) {
		
		String via = PropertyHelper.getConnection(source.getStationLine(), destination.getStationLine());
		List<String> viaResolvedNames = PropertyHelper.getSudoName(via);
		String viaResolvedDes = "";
		String viaResolvedSrc = "";
		if(viaResolvedNames.get(0).indexOf(source.getStationLine())==0)
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
		switch(distance){
		case 1:
			//cost += calCost(source, distance-3);
			distance +=getDistance(vrs, destination);
			if((distance-tempDistance)>2)
				cost += cc.calCost(vrs, (distance-tempDistance-2));
			else 
				cost = 10;
			break;
		case 2:
			distance +=getDistance(vrs, destination);
			if((distance-tempDistance)>1)
				cost += cc.calCost(vrs, (distance-tempDistance-1));
			else 
				cost = 10;
			break;
		case 3:
			distance +=getDistance(vrs, destination);
			if((distance-tempDistance)>0)
				cost += cc.calCost(vrs, (distance-tempDistance-0));
			else 
				cost = 10;
			break;
		default:
			cost += cc.calCost(source, distance-3);
			distance +=getDistance(vrs, destination);
			cost += cc.calCost(vrs, distance-tempDistance);
		}
		
		
	}
	public int getDistance(StationI source, StationI destination)
	{
		int sourceValue = Integer.parseInt(source.getStationCode().substring(1));
		int destinationValue = Integer.parseInt(destination.getStationCode().substring(1));
		return Math.abs(sourceValue-destinationValue);
	}
}
