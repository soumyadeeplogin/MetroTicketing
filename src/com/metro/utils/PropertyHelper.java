package com.metro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

public class PropertyHelper {
	
	final static String StationName = "StationNames.properties";
	final static String ConnectorMap = "connectors.properties";
	
	public static String getStationName(String code)
	{
		return readProp(StationName, code);
	}
	
	public static String resolveLine(char Line)
	{
		switch (Line){
			case 'A':
				return "L1";
			case 'B':
				return "L2";
			case 'C':
				return "L3";
			default :
				return null;
		}
	}

	public static String getConnection(char LineI, char LineII) 
	{
		//String Lines = "";
		String LineA = resolveLine(LineI)+"&"+resolveLine(LineII);	
		String LineB = resolveLine(LineII)+"&"+resolveLine(LineI);
		return (readProp(ConnectorMap, LineA)!=null)?readProp(ConnectorMap, LineA):readProp(ConnectorMap, LineB);
	}
	
	public static List<String> getSudoName(String code) 
	{
		List<String> sudo = new ArrayList<String>();
		String connects = readProp(ConnectorMap, code);
		StringTokenizer st = new StringTokenizer(connects,"&");
		while(st.hasMoreTokens()) {
			sudo.add(0, st.nextToken());
			sudo.add(1, st.nextToken());
		}
		return sudo;
	}
	
	private static String readProp(String filename, String key)
	{
		String value = null;
		Properties prop = new Properties();
		InputStream input = null;
		try 
		{
			input = new FileInputStream(filename);
			prop.load(input);
			value = prop.getProperty(key);
		}  catch (IOException ex) {
			//ex.printStackTrace();
			
		} catch (NullPointerException ex) {
			//ex.printStackTrace();
			
		}
		/*finally {
			if (input != null) {
				try {
					//input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}*/
		return value;
	}
}
