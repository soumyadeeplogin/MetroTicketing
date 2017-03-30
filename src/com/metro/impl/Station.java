package com.metro.impl;

import java.util.List;

import com.metro.interfacer.StationI;
import com.metro.utils.PropertyHelper;

public class Station implements StationI{

	
	String code = null;
	String stationName = null;
	
	//String[] connectingX = {null,null};
	
	public Station(String code) {
		this.code = code;
		setStationName();
	}
	
	@Override
	public void setStationName() {
		stationName = PropertyHelper.getStationName(code);	
	}

	@Override
	public String getStationName() {
		return (stationName!=null)?stationName:"undefined";		
	}
	
	@Override
	public String getStationCode() {
		return code;
	}

	@Override
	public char getStaionLine() {
		return code.charAt(0);
	}

	@Override
	public void setXattribute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInterChangeStaion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInterchangeStation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> findXstation() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
