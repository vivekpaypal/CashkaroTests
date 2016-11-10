package com.cashkaro.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cashkaro.test.DriverSession;
import com.thoughtworks.selenium.webdriven.Windows;
import com.thoughtworks.selenium.webdriven.commands.WaitForPopup;

public class BasePage {
	
	
	public BasePage() {
		
	}
	
	public void waitForElement(WebElement element){
		(new WebDriverWait(DriverSession.getDriver(), 10))
				  .until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitForElementVisibility(WebElement element){
		(new WebDriverWait(DriverSession.getDriver(), 10))
		  .until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void click(String id){
		
		JavascriptExecutor jsExecutor = ((JavascriptExecutor)DriverSession.getDriver());
		jsExecutor.executeScript("document.getElementById('"+id+"').click()");
	}
	
	public String moveToOtherWindow(){
		
		
		String current = DriverSession.getDriver().getWindowHandle();
		
		Iterator<String> it = DriverSession.getDriver().getWindowHandles().iterator();
		
		while(it.hasNext()){
			String handle =it.next();
			if(!handle.equals(current)){
				
				DriverSession.getDriver().switchTo().window(handle);
				break;
			}
		}
		return current;
	}
	
	
	public void moveToFrame(WebElement frame){
		
		DriverSession.getDriver().switchTo().frame(frame);
		
		
		
	}
	
	public void moveToDefaultScreen(){
		DriverSession.getDriver().switchTo().defaultContent();
	}
	
	

}
