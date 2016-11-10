package com.cashkaro.test;

import java.io.IOException;
import java.util.Properties;



public class BaseConfiguration {
	
	protected DriverSession session;
	protected static TestConfig config;
	
	public static class Configuration {
		public static  String BROWSER_KEY ="browser";
		public static  String URL_KEY ="url";
		
		private static Properties conf;
		
	    private static void readPropertyFile(){
	    	
	    	 conf = new Properties();
	    	try {
				conf.load(ClassLoader.getSystemResourceAsStream("test.properties"));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    public static TestConfig getConfigurations(){
	    	if(config != null){
	    		return config;
	    	}
	    	readPropertyFile();
	    	 config = new TestConfig();
	     	config.setBrowser(conf.getProperty(Configuration.BROWSER_KEY));
	     	config.setUrl(conf.getProperty(Configuration.URL_KEY));
			return config;
	    }
				
		
	}
    
	
	
   
    
   
   
}
