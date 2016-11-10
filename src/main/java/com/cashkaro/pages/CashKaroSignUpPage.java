package com.cashkaro.pages;

import java.util.Iterator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cashkaro.test.DriverSession;

public class CashKaroSignUpPage extends BasePage{
	
	@FindBy(how = How.ID, using = "firstname")
    private WebElement firstNameTextField;
	@FindBy(how = How.ID, using = "email")
    private WebElement emailTextField;
	@FindBy(how = How.ID, using = "con_email")
    private WebElement confirmEmailTextField;
	@FindBy(how = How.ID, using = "pwd-txt")
    private WebElement passwordTextField;
	@FindBy(how = How.ID, using = "to_be_check")
    private WebElement captchaTextFiled;
	@FindBy(how = How.ID, using = "join_free_submit")
    private WebElement joinButton;
	
	@FindBy(how = How.ID, using = "close_and_go_fb")
    private WebElement fbButton;
	@FindBy(how = How.LINK_TEXT,using= "MY ACCOUNT")
    private WebElement accountButton;
	
	public static CashKaroSignUpPage getInstance(){
		
		return PageFactory.initElements(DriverSession.getDriver(),CashKaroSignUpPage.class);
	}
	
	/*
	 * Normal sign up using the email.
	 * 
	 */
	public void signUp(String firstName,String email,String pwd){
		
		firstNameTextField.clear();
		firstNameTextField.sendKeys(firstName);
		waitForElementVisibility(emailTextField);
		emailTextField.sendKeys(email);
		waitForElementVisibility(confirmEmailTextField);
		confirmEmailTextField.sendKeys(email);
	    waitForElementVisibility(passwordTextField);
		passwordTextField.sendKeys(pwd);
		
		//TODO captcha cannot be automated. Need to call captcha services and get the correct answer.
		 waitForElementVisibility(captchaTextFiled);
		captchaTextFiled.sendKeys("");
		joinButton.click();
		waitForElementVisibility(accountButton);
		
	}
	
	public void facebookSignUp(String phoremail,String pwd) throws InterruptedException{
		
		fbButton.click();
		
		String current = moveToOtherWindow();
		FacebookLoginPage fblogin = FacebookLoginPage.getInstance();
		fblogin.loginUsingFacebook(phoremail,pwd);
		DriverSession.getDriver().close();
		DriverSession.getDriver().switchTo().window(current);
		waitForElementVisibility(accountButton);
		
	}
	
	
	
	

}
