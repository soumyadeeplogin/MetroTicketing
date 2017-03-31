package com.metro.impl;

import com.metro.interfacer.StationI;
import com.metro.utils.PropertyHelper;

public class Station implements StationI{

	String code = null;
	String stationName = null;
	boolean isX = false;
	
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
	public char getStationLine() {
		return code.charAt(0);
	}

	@Override
	public void setInterChangeStaion() {
		isX = (code.charAt(0)=='X')?true:false;	
		if(isX) {
			setSudoName();
		}
	}

	@Override
	public boolean isInterchangeStation() {
		return isX;
	}

	@Override
	public void setSudoName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StationI getSudoName(int index) {
		// TODO Auto-generated method stub
		return null;
	}
		
}
