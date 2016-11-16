package com.cashkaro.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cashkaro.test.DriverSession;

public class AccountPage extends BasePage {
	
	
	@FindBy(how = How.TAG_NAME, using = "aside")
    private WebElement earningDetails;
	
	public static AccountPage getInstance(){
		
		
		return PageFactory.initElements(DriverSession.getDriver(),AccountPage.class);
		
	}
	

	
	
	public String getTotalEarning(){
		
		List<WebElement>  earnings = earningDetails.findElements(By.xpath(".//li/span[@class='txt']"));
		return earnings.get(0).getText();
	}
	
}
	
	