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
import com.qa.pages.BrandPage;
import com.qa.pages.CategoryPage;
import com.qa.pages.HomePage;
import com.qa.pages.ItemsPage;
import com.qa.utils.TestUtils;

public class ItemsPageTest extends TestBase{

	HomePage homePage;
	CategoryPage categoryPage;
	ItemsPage itemsPage;
	BrandPage brandPage;
	
	public ItemsPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeMethod
	@BeforeClass
	public void setUp() throws IOException, InterruptedException{
		initialization();
		homePage = new HomePage();
		categoryPage = homePage.selectPage(prop.getProperty("page"));
		itemsPage = categoryPage.selectCategory(prop.getProperty("category"),prop.getProperty("item"));	
	
	}

	
	@Test(priority=1)
	public void itemPageCheck() throws InterruptedException{
		String itemTitle = ItemsPage.verifyItemPageTitle();
		//AssertJUnit.assertTrue(itemTitle.contains(prop.getProperty("item")),"Title displayed doesn't match");
	}
	
	@Test(priority=2)
	public void searcValueTest() throws InterruptedException{
		String searchValue = ItemsPage.itemSearch(prop.getProperty("search"));
		//AssertJUnit.assertTrue(searchValue.contains("RESULTS"),"Text displayed doesn't match");
	}
	
	
	@Test(priority=3)
	public void itemPageTest() throws IOException, InterruptedException{
		brandPage = itemsPage.selectBrand(prop.getProperty("brand"));
	}
	
	@AfterMethod
	@AfterClass
	public void tearDown(){
		//driver.quit();
		shutDown();

	}
	

}
