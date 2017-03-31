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

	public char getStationLine();

	public String getStationCode();

	public void setInterChangeStaion();

	public boolean isInterchangeStation();

	public void setSudoName();

	public StationI getSudoName(int index);

}
