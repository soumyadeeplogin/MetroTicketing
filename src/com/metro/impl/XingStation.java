package com.metro.impl;

import java.util.ArrayList;
import java.util.List;

import com.metro.interfacer.StationI;
import com.metro.utils.PropertyHelper;

public class XingStation extends Station{

	public XingStation(String code) {
		super(code);
		setInterChangeStaion();
	}
	
	boolean isX = false;
	List<StationI> sudoName = new ArrayList<StationI>(); 
	
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
		List<String> names = PropertyHelper.getSeduName(code);
		StationI seduOne = new Station(names.get(0));
		StationI seduTwo = new Station(names.get(1));
		sudoName.add(seduOne);
		sudoName.add(seduTwo);
	}
	
	@Override
	public StationI getSudoName(int index) {
		return sudoName.get(index);
	}
}
