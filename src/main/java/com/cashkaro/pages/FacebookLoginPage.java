package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cashkaro.test.DriverSession;

public class FacebookLoginPage extends BasePage {
	
	
	@FindBy(how = How.ID, using = "email")
    private WebElement emailTextField;
	@FindBy(how = How.ID, using = "pass")
    private WebElement passwordTextField;
	@FindBy(how = How.NAME, using = "login")
    private WebElement loginButton;
	@FindBy(how = How.NAME, using = "__CONFIRM__")
    private WebElement confirmButton;
	
	
	public static FacebookLoginPage getInstance(){
		
		
		return PageFactory.initElements(DriverSession.getDriver(),FacebookLoginPage.class);
		
	}
	
	public void loginUsingFacebook(String emailorPhone,String pwd){
		
		emailTextField.sendKeys(emailorPhone);
		passwordTextField.sendKeys(pwd);
		loginButton.click();
			
		
	}

}
