package com.metro.impl;

import java.util.List;

import com.metro.utils.PropertyHelper;

public class XingStation extends Station{

	public XingStation(String code) {
		super(code);
		setXattribute();
		setInterChangeStaion();
	}
	
	boolean isX = false;
	List<String> connector; 
	
	@Override
	public void setXattribute() {
		//connector = PropertyHelper.getConnection(code);
	}
	
	@Override
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
