/**
 * 
 */
package com.metro.interfacer;

import java.util.List;

/**
 * @author Soumyadeep
 *
 */
public interface StationI {
	
	public void setStationName();
	public String getStationName();
	public char getStaionLine();
	public String getStationCode();
	public void setXattribute();
	public void setInterChangeStaion();
	public boolean isInterchangeStation();
	public List<String> findXstation();
		
}
