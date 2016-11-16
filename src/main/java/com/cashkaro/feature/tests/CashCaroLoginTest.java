package com.cashkaro.feature.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cashkaro.pages.AccountPage;
import com.cashkaro.pages.CashKaroHomePage;
import com.cashkaro.pages.ProductPage;
import com.cashkaro.test.BaseTest;
import com.cashkaro.utility.DataInfo;
import com.cashkaro.utility.ExcelUtility;


public class CashCaroLoginTest extends BaseTest {
	
	
	
	/*
	 * Sign Up using email.
	 * 
	 * 
	 */
	@Test
	public void signUpEmailTest() throws InterruptedException{
		
		CashKaroHomePage page = CashKaroHomePage.getInstance();
		page.signUp("test",Math.random()+"@abc.com","test123");
	    
	    
	}
	
	/*
	 * Sign Up using facebook.
	 * Using a existing user and it will throw an error.
	 * If we need dynamic users, facebook API should be used.
	 * 
	 */
	@Test
	public void signUpFacebookTest() throws InterruptedException{
		
		CashKaroHomePage page = CashKaroHomePage.getInstance();
			
		page.fBsignUp("vivek100test@gmail.com","test-11235"); 
	}
	
	
	/*
	 * Sign in Using facebook.
	 * 
	 */
	@Test
	public void signInUsingFacebook() throws InterruptedException{
		
		CashKaroHomePage page = CashKaroHomePage.getInstance();
			
		page.signInFacebook("vivek100test@gmail.com","test-11235"); 
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
	
	/*
	 * Login
	 * Search product
	 * Select a deal
	 * Check earnings
	 * 
	 */
    @Test
	public void testEarnings() throws InterruptedException{
		
		CashKaroHomePage homePage = CashKaroHomePage.getInstance();
		homePage.signIn("test@test1234.com","test1234");
		ProductPage productPage = homePage.searchProduct("Baby Touch: Flip-Flap Book");
		productPage.clickOnProdcut();
		productPage.grabDeal();
		
		AccountPage accountPage = AccountPage.getInstance();
		homePage.openAccountsPage();
		Assert.assertTrue(accountPage.getTotalEarning().contains("0.00"));
	}
	
	
	
	/*
	 * Login
	 * Search product  (data provided through the data provider (Excel))
	 * Select a deal
	 * 
	 * 
	 */
	@Test(dataProvider="searchData")
	public void testWithDifferentData(DataInfo data) throws InterruptedException{
		
		CashKaroHomePage homePage = CashKaroHomePage.getInstance();
		homePage.signIn("test@test1234.com","test1234");
		ProductPage productPage = homePage.searchProduct(data.getSearchData());
		productPage.clickOnProdcut();
		
	}
	
	
	
	
	/*
	 * Data provider reading the data from excel sheet.
	 */
	@DataProvider(name="searchData")
	public Iterator<Object[]> getData() throws Exception{
		
		ExcelUtility<DataInfo> data = new ExcelUtility<DataInfo>("./src/test/resources/testData/","SampleTestData.xlsx");
	
		List<Object []> list=  new ArrayList<Object[]>();
		List<DataInfo> l =  data.getData(DataInfo.class);
		for(DataInfo d: l){
		Object [] o = {d};	
		list.add(o);
		}
		
		return list.iterator();
	}
	
	

}
