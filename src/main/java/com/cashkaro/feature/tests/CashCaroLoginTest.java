package com.cashkaro.feature.tests;

import org.testng.annotations.Test;

import com.cashkaro.pages.CashKaroHomePage;
import com.cashkaro.pages.CashKaroSignUpPage;
import com.cashkaro.test.BaseTest;


public class CashCaroLoginTest extends BaseTest {
	
	
	
	/*
	 * Sign Up using email.
	 * 
	 * 
	 */
	@Test
	public void signUpEmailTest() throws InterruptedException{
		
		CashKaroHomePage page = CashKaroHomePage.getInstance();
		CashKaroSignUpPage signUp = page.OpenSignUpPage();		
	    signUp.signUp("test",Math.random()+"@abc.com","test123");
	    
	    
	}
	
	/*
	 * Sign Up using facebook.
	 * Using a default test facebook user. But it does not have any access.
	 * 
	 */
	@Test
	public void signUpFacebookTest() throws InterruptedException{
		
		CashKaroHomePage page = CashKaroHomePage.getInstance();
		CashKaroSignUpPage signUp = page.OpenSignUpPage();		
	    signUp.facebookSignUp("uirycgvkxr_1478766096@tfbnw.net","sunday-1"); 
	}
	
	/*
	 * Sign in using username and password.
	 * 
	 */
	@Test
	public void signInTest() throws InterruptedException{
		
		CashKaroHomePage homePage = CashKaroHomePage.getInstance();
		homePage.signIn("test@test1234.com","test1234");
	}
	
	
	/*
	 * Try to get forgotten password.
	 * 
	 */
	@Test
	public void forgotPassowrdTest() throws InterruptedException{
		
		CashKaroHomePage homePage = CashKaroHomePage.getInstance();
		homePage.forgotPassword("test@test123.com");
	}
	

}
