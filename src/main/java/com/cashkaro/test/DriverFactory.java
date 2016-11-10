package com.cashkaro.test;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
	
	
	
	
	private DriverFactory() {
		
	}
	
	public static WebDriver getInstance(String browser){
		String os =System.getProperty("os.name");
		
		if(browser.equals("firefox")){
			
			return new FirefoxDriver();
			
		}
		
		if(browser.equals("chrome")){
			
			
			
			if(os.equals("Mac OS X")){
			System.setProperty("webdriver.chrome.driver", new File("./src/test/resources/drivers/mac/chromedriver").getAbsolutePath());	
			}else{
			System.setProperty("webdriver.chrome.driver", new File("./src/test/resources/drivers/win/chromedriver.exe").getAbsolutePath());	
				
			}
			
			return new ChromeDriver();
					
		}


		if(browser.equals("internetexplorer")){
			
			File driver = new File("./src/main/resources/drivers/IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", driver.getAbsolutePath());       
			return new InternetExplorerDriver();
			
		}
		return new InternetExplorerDriver();
	}

}
