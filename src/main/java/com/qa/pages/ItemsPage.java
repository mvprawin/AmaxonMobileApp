package com.qa.pages;

import com.google.common.collect.ImmutableMap;
import com.qa.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class ItemsPage extends TestBase{
	
int a=0;
	public ItemsPage() throws IOException {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.EditText[@resource-id ='in.amazon.mShop.android.shopping:id/rs_search_src_text']")
	public static MobileElement itemTitle;
	
	@FindBys(@FindBy(xpath = "//android.view.View[@resource-id='search']/child::*"))
	public List<MobileElement> brandList;	
	
	@FindBy(xpath = "//android.view.View[@index='2']")
	public static MobileElement search;
	
	
	public static String verifyItemPageTitle() {
		return itemTitle.getText().toString();
	}
	
	public static String itemSearch(String searchname) throws InterruptedException {
		itemTitle.sendKeys(searchname);
			Thread.sleep(2000);
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
				Thread.sleep(2000);
		return search.getText().toString();
		
	}
	
	
	public BrandPage selectBrand(String brand) throws IOException, InterruptedException{
		
		boolean cflag =false;
		do {
			
			for(MobileElement brand_name:brandList) {
				Thread.sleep(3000);
				String options = brand_name.getText();
				System.out.println(options);
				if(options.contains(brand)) {
					brand_name.click();
					Thread.sleep(2000);
					scroll(0.5,0.8,0.5,0.2);
					cflag = true;
					break;

				}
			}
			scrollDown();
			Thread.sleep(2000);
			a++;
		}while(!cflag);
		return new BrandPage();
	}

}
