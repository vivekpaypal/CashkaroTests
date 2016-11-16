package com.cashkaro.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cashkaro.test.DriverSession;

import junit.framework.Assert;

public class ProductPage extends BasePage {
	
	
	@FindBy(how = How.LINK_TEXT, using = "GRAB DEAL")
    private WebElement grabButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='fw fl product_detail_slider mb']")
	private WebElement productSection;
	
	public static ProductPage getInstance(){
		
		
		return PageFactory.initElements(DriverSession.getDriver(),ProductPage.class);
		
	}
	
	public void clickOnProdcut(){
				List<WebElement> productList = productSection.findElements(By.xpath(".//li//img"));

		
		if(!productList.isEmpty()){
			productList.get(0).click();
		}else{
			Assert.fail("There is not product listed");
		}
	}
	
	public void grabDeal(){
		grabButton.click();
		String defaultWin = moveToOtherWindow();
		waitForElement(accountButton);
		DriverSession.getDriver().close();
		DriverSession.getDriver().switchTo().window(defaultWin);
	}
	
}
	
	