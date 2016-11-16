package com.cashkaro.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	
	@FindBy(how = How.ID_OR_NAME,using= "search_store")
    private WebElement searchTextField;
	@FindBy(how = How.ID,using= "searchFormSubmit")
    private WebElement searchButton;
	
	@FindBy(how = How.LINK_TEXT,using= "MY EARNINGS")
    private WebElement myEarningsLink;
	
	
	 
	
	
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
	
	public void signInFacebook(String email,String pwd){
		signInLink.click();
		moveToFrame(loginFrame);
		CashKaroSignUpPage signUp  = CashKaroSignUpPage.getInstance();
		waitForElement(signUp.fbButton);
		signUp.fbButton.click();
		String current = moveToOtherWindow();
		FacebookLoginPage fblogin = FacebookLoginPage.getInstance();
		fblogin.loginUsingFacebook(email, pwd);
		
		DriverSession.getDriver().switchTo().window(current);
		
	}
	
	public void fBsignUp(String phoremail,String pwd) throws InterruptedException{
		OpenSignUpPage();
		CashKaroSignUpPage signUp  = CashKaroSignUpPage.getInstance();
		signUp.facebookSignUp(phoremail, pwd);
		
		/*
		 * Verification not added as facebook login is already registered and will result in error page.
		 */
	}
	public void signUp(String firstName,String email,String pwd){
		OpenSignUpPage();
		CashKaroSignUpPage signUp  = CashKaroSignUpPage.getInstance();
		signUp.signUp(firstName, email, pwd);
		
	}
	
	public void forgotPassword(String email){
		signInLink.click();
		moveToFrame(loginFrame);
		forgotPassLink.click();
		waitForElementVisibility(forgotPassEmailTextField);
		forgotPassEmailTextField.sendKeys(email);
		sendForgotPassButton.click();
		Assert.assertTrue(successLabel.getText().contains("Password sent"));
		
	}
	
	
	public static class CashKaroSignUpPage extends BasePage{
		
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
			 //waitForElementVisibility(captchaTextFiled);
			//captchaTextFiled.sendKeys("");
			joinButton.click();
			waitForElementVisibility(accountButton);
			
		}
		
		public void facebookSignUp(String phoremail,String pwd) throws InterruptedException{
			
			CashKaroSignUpPage signUp  = CashKaroSignUpPage.getInstance();
			signUp.fbButton.click();
			
			
			
			String current = moveToOtherWindow();
			FacebookLoginPage fblogin = FacebookLoginPage.getInstance();
			fblogin.loginUsingFacebook(phoremail,pwd);
			
			DriverSession.getDriver().switchTo().window(current);
			
			
		}
		
		
		
		
		
		

	}
	
	public ProductPage searchProduct(String text){
		
		
		searchTextField.sendKeys(text);
		searchButton.click();
		
		return ProductPage.getInstance();
	}

	
	
	public void openAccountsPage(){
		
		Actions action = new Actions(DriverSession.getDriver());
		action.moveToElement(accountButton).click(myEarningsLink).build().perform();;
	}
}
