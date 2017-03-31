package com.metro.main;
import com.metro.interfacer.StationI;

public class CalculateCost {
	public double calCost(StationI source, int distance)
	{
		switch (source.getStationLine())
		{
		case 'A':
			return distance*2.5;
		case 'B':
			return distance*2.0;
		case 'C':
			return distance*3.0;
		default:
			return 0.0;
		}
	}
}
