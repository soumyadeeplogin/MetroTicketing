package com.metro.impl;

import java.util.List;

import com.metro.utils.PropertyHelper;

public class XingStation extends Stations{

	public XingStation(String code) {
		super(code);
		// TODO Auto-generated constructor stub
	}
	
	boolean isX = false;
	List<String> connector; 
	
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
