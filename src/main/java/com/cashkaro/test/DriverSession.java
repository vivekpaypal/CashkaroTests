package com.cashkaro.test;

import org.openqa.selenium.WebDriver;

public class DriverSession {
	
	
	
	
	private  static ThreadLocal<WebDriver> session = new ThreadLocal<WebDriver>() {
		
		protected WebDriver initialValue() {
			
			return DriverFactory.getInstance(BaseConfiguration.Configuration.getConfigurations().getBrowser());
		};
		
	};
	
	
	



	

    public static void setDriver(){
    	session.set(DriverFactory.getInstance(BaseConfiguration.Configuration.getConfigurations().getBrowser()));
    }

	public static WebDriver getDriver(){
		
		return session.get(); 
		
	}
	
	

}
