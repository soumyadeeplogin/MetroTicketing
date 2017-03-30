package com.metro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
		String Lines = "";
		Lines = resolveLine(LineI)+"&"+resolveLine(LineII);		
		return readProp(ConnectorMap, Lines);
	}
	
	public static List<String> getSeduName(String code) 
	{
		List<String> sedu = null;
		String connects = readProp(ConnectorMap, code);
		StringTokenizer st = new StringTokenizer(connects,"&");
		sedu.add(0, st.nextToken());
		sedu.add(1, st.nextToken());
		return sedu;
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
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
