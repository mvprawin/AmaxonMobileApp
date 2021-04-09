package com.qa.pages;

import com.qa.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class BrandPage extends TestBase{
	
int a=0;
	public BrandPage() throws IOException {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.EditText[@resource-id ='in.amazon.mShop.android.shopping:id/rs_search_src_text']")
	public static MobileElement itemTitle;
	
	@FindBys(@FindBy(xpath = "//android.view.View[@resource-id='search']/child::*"))
	public List<MobileElement> brandList;	
	
	
	public static String verifyItemPageTitle() {
		return itemTitle.getText().toString();
	}
	
	public String itemSearch(String name) {
		itemTitle.sendKeys(name);
		itemTitle.submit();		
		return null;
		
	}
	
	
}
