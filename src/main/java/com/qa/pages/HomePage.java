package com.qa.pages;


import com.google.common.collect.ImmutableMap;
import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase{
	
	int a=0;	
	
	public HomePage() throws IOException {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.widget.ImageView[@resource-id='in.amazon.mShop.android.shopping:id/chrome_action_bar_burger_icon']")
	public MobileElement menu;
	
	@FindBy(xpath = "//android.widget.ImageView[@resource-id='in.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo']")
	public MobileElement applogo;
	
	@FindBy(xpath = "//android.widget.EditText[@resource-id='in.amazon.mShop.android.shopping:id/rs_search_src_text']")
	public MobileElement search;
	
	@FindBy(xpath = "//android.widget.TextView[@resource-id='in.amazon.mShop.android.shopping:id/chrome_action_bar_cart_count']")
	public MobileElement cart;
	
	@FindBys(@FindBy(xpath = "//android.widget.TextView[@resource-id='in.amazon.mShop.android.shopping:id/drawer_item_title']"))
	public List<MobileElement> list_items;	
	
	@FindBy(xpath = "//android.widget.LinearLayout[@resource-id='in.amazon.mShop.android.shopping:id/anp_drawer_item']")
	public MobileElement list_item;	
	
	@FindBy(xpath = "//android.view.View[@index='3']")
	public MobileElement result;
	
	
	
		
	
	public boolean validateLogoImg() {
		waitforElementVisibility(applogo);
		return applogo.isDisplayed();
		
	}
	
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	
	public String cartCount() throws InterruptedException{
		waitforElementVisibility(cart);
		return cart.getText().toString();
	}
	
	
	public void clickMenu(){
		waitforElementVisibility(menu);
		waitforElementClickable(menu);
		menu.click();
	}
	
	
	public String itemSearch(String searchname) throws InterruptedException {
		search.sendKeys(searchname);
		Thread.sleep(2000);

		
		((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		//driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "done"));
				Thread.sleep(2000);
		return result.getText().toString();
		
	}
	
	
	public CategoryPage selectPage(String page) throws IOException, InterruptedException{
		clickMenu();
		
		boolean flag =false;
		do {
			for(MobileElement list:list_items) {
				Thread.sleep(3000);
				String page_name = list.getText();
				System.out.println(page_name);
				System.out.println(page);				
				if(page_name.equalsIgnoreCase(page)) {
					list.click();
					flag = true;
					break;
				}

			}
				scroll(0.3,0.8,0.3,0.2);
				Thread.sleep(2000);
				a++;
			
		}while(!flag);
		
		
					    	
		return new CategoryPage();
	
	}
	
	

	


}
