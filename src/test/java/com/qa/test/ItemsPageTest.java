package com.qa.test;

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
		Assert.assertTrue(itemTitle.contains(prop.getProperty("item")),"Title displayed doesn't match");
	}
	
	@Test(priority=2)
	public void searcValueTest() throws InterruptedException{
		String searchValue = ItemsPage.itemSearch(prop.getProperty("search"));
		Assert.assertTrue(searchValue.contains("RESULTS"),"Text displayed doesn't match");
	}
	
	
	@Test(priority=3)
	public void itemPageTest() throws IOException, InterruptedException{
		brandPage = itemsPage.selectBrand(prop.getProperty("brand"));
	}
	
	@AfterClass
	public void tearDown(){
		//driver.quit();
		shutDown();

	}
	

}
