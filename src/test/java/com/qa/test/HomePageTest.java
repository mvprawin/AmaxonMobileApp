package com.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.base.TestBase;
import com.qa.pages.CategoryPage;
import com.qa.pages.HomePage;
import com.qa.utils.TestUtils;

public class HomePageTest extends TestBase{

	HomePage homePage;
	CategoryPage categoryPage;
	
	public HomePageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	@BeforeClass
	public void setUp() throws IOException, InterruptedException{
		initialization();
		homePage = new HomePage();
		Thread.sleep(30000);
	}
	
	
	@Test(priority=1)
	public void crmLogoImageTest(){
		boolean flag = homePage.validateLogoImg();
		AssertJUnit.assertTrue(flag);
	}
	
	@Test(priority=2)
	public void cartValueTest() throws InterruptedException{
		String cartValue = homePage.cartCount();
		AssertJUnit.assertEquals(cartValue, "9","Cart count not matched");
	}
	
	@Test(priority=3)
	public void homePageTest() throws IOException, InterruptedException{
		categoryPage = homePage.selectPage("Shop by Category");
	}

	
	public void homePagecheck() throws IOException, InterruptedException{
		String value = homePage.itemSearch(prop.getProperty("search"));
		AssertJUnit.assertEquals(value.contains("Nivia Storm Football"),"Result not matched");
	}
	
	
	
	
	
	@AfterMethod
	@AfterClass
	public void tearDown(){
		//driver.quit();
		shutDown();

	}
	

}
