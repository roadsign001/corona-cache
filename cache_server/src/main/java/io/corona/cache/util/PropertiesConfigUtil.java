package io.corona.cache.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertiesConfigUtil {

	private static ResourceBundle localResource;
	static{
		Locale locale = Locale.getDefault();
    	localResource = ResourceBundle.getBundle("application", locale);
	}
	
	public static String getProperty(String key){    	
    	return localResource.getString(key); 
	}
	
	public static Integer getPropertyInt(String key){    	
    	String value = localResource.getString(key); 
    	return Integer.valueOf(value);
	}
	public static Boolean getPropertyBoolean(String key){    	
    	String value = localResource.getString(key); 
    	return Boolean.valueOf(value);
	}
}
