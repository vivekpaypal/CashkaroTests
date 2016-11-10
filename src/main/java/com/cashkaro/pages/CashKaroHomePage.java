package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.cashkaro.test.DriverSession;

import junit.framework.Assert;

public class CashKaroHomePage extends BasePage{
	
	@FindBy(how = How.LINK_TEXT,using= "JOIN FREE")
    private WebElement joinFreeButton;
	@FindBy(how = How.LINK_TEXT,using= "SIGN IN")
    private WebElement signInLink;
	@FindBy(how = How.ID_OR_NAME,using= "uname")
    private WebElement emailTextField;
	@FindBy(how = How.ID,using= "pwd-txt")
    private WebElement passwordTextField;
	@FindBy(how = How.ID,using= "sign_in")
    private WebElement signInButton;
	
	@FindBy(how = How.LINK_TEXT,using= "MY ACCOUNT")
    private WebElement accountButton;
	@FindBy(how = How.LINK_TEXT,using= "Forgot Password?")
    private WebElement forgotPassLink;
	@FindBy(how =How.XPATH, using="//iframe[@class='cboxIframe']")
	private WebElement loginFrame;
	
	@FindBy(how = How.ID,using= "from_email")
    private WebElement forgotPassEmailTextField;
	
	@FindBy(how = How.ID,using= "submit_req")
    private WebElement sendForgotPassButton;
	@FindBy(how = How.XPATH,using= "//*[@class='popup forgot_ps']/div[@class='f_pass_s']")
	private WebElement successLabel;
	
	 
	
	
	public static CashKaroHomePage getInstance(){
		
		
		return PageFactory.initElements(DriverSession.getDriver(),CashKaroHomePage.class);
		
	}
	
	public CashKaroSignUpPage OpenSignUpPage(){
		
		joinFreeButton.click();
		return CashKaroSignUpPage.getInstance();
	}
	
	/*
	 * Sign into the application using user name and password
	 * Verify if the account button available on the page after login.
	 */
	public void signIn(String email,String pwd){
		
		signInLink.click();
		moveToFrame(loginFrame);
		waitForElementVisibility(emailTextField);
		emailTextField.sendKeys(email);
		passwordTextField.sendKeys(pwd);
		signInButton.click();
		moveToDefaultScreen();
		waitForElementVisibility(accountButton);
	}
	
	public void forgotPassword(String email){
		
		signInLink.click();
		waitForElementVisibility(loginFrame);
		moveToFrame(loginFrame);
		forgotPassLink.click();
		waitForElementVisibility(forgotPassEmailTextField);
		forgotPassEmailTextField.sendKeys(email);
		sendForgotPassButton.click();
		Assert.assertTrue(successLabel.getText().contains("Password sent"));
		
	}
	

}
