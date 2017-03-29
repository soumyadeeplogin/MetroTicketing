package com.metro.impl;

import java.util.List;

import com.metro.interfacer.Staions;
import com.metro.utils.PropertyHelper;

public class Stations implements Staions{

	String code = null;
	String stationName = null;
	boolean isX = false;
	List<String> connector; 
	//String[] connectingX = {null,null};
	
	public Stations(String code) {
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
		connector = PropertyHelper.getConnection(code);
	}
	
	public List<String> findXstation() {
		return connector;
	}
	
	@Override
	public void setInterChangeStaion() {
		isX = (code.charAt(0)=='X')?true:false;	
		if(isX) {
			setXattribute();
		}
	}

	@Override
	public boolean isInterchangeStation() {
		return isX;
	}
	
	
}
