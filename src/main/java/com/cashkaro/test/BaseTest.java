package com.cashkaro.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	@BeforeMethod
	public static void openBrowser() {
		 
    	DriverSession.setDriver();
    	DriverSession.getDriver().get(BaseConfiguration.Configuration.getConfigurations().getUrl());
    	
    }
    
    
    @AfterMethod
    public static void closeBrowser(){
    	
    	DriverSession.getDriver().close();
    }

}
