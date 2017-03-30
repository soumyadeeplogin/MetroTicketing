package com.metro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class PropertyHelper {
	public static String getStationName(String code)
	{
		return null;
	}

	public static List<String> getConnection(String code) 
	{
		
		return null;
	}
	
	public String readProp(String filename, String key)
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
