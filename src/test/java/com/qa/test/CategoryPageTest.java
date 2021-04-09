package com.qa.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.base.TestBase;
import com.qa.pages.CategoryPage;
import com.qa.pages.HomePage;
import com.qa.pages.ItemsPage;
import com.qa.utils.TestUtils;

public class CategoryPageTest extends TestBase{

	HomePage homePage;
	CategoryPage categoryPage;
	ItemsPage itemsPage;

	
	public CategoryPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@BeforeClass
	public void setUp() throws IOException, InterruptedException{
		initialization();
		homePage = new HomePage();
		categoryPage = homePage.selectPage(prop.getProperty("page"));
	}
	
	
	@Test(priority=1)
	public void pageTitleTest(){
		String pageTitle = categoryPage.verifyCategoryPageTitle();
		Assert.assertEquals(pageTitle, "Explore all Categories","Title displayed doesn't match");
	}
	
	
	@Test(priority=2)
	public void categoryPageTest() throws IOException, InterruptedException{
		itemsPage = categoryPage.selectCategory(prop.getProperty("category"),prop.getProperty("item"));		
	}
	
	
	@AfterClass
	public void tearDown(){
		//driver.quit();
		shutDown();

	}
	

}
