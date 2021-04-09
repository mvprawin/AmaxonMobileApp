package com.qa.pages;

import com.qa.base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage extends TestBase{
	int i,a =0;
	

	public CategoryPage() throws IOException {
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@FindBy(xpath = "//android.view.View[@text='Explore all Categories']")
	public MobileElement title;

	
	@FindBys(@FindBy(xpath = "//android.view.View[@index='2']"))
	public List<MobileElement> categoryList;	
	
	@FindBys(@FindBy(xpath = "//android.view.View[@resource-id='sbdCategory13']/child::*"))
	public List<MobileElement> itemList;	

	
	
	public String verifyCategoryPageTitle(){
		return title.getText().toString();
	}
	
	
	
	public ItemsPage selectCategory(String category,String item) throws IOException, InterruptedException{
		
		boolean cflag =false;
		do {
			
			for(MobileElement category_name:categoryList) {
				Thread.sleep(2000);
				String option = category_name.getText();
				System.out.println(option);
				if(option.contains(category)) {
					category_name.click();
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
		

		boolean iflag =false;
		
		do {
			for(MobileElement item_name:itemList) {
				Thread.sleep(2000);
				String value = item_name.getText();
				System.out.println(value);
				if(value.equalsIgnoreCase(item)) {
					item_name.click();
					iflag = true;
					break;
				}
			}
			scroll(0.5,0.8,0.5,0.2);
			Thread.sleep(2000);
			i++;
			
		}while(!iflag);					
							    	
		return new ItemsPage();
	
	}
	
	
	
	
	


}
