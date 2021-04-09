package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestBase {
	public static AndroidDriver<MobileElement> driver;
	public static AppiumDriverLocalService service;
	public static Properties prop;
	
	
	public TestBase() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/qa/config/Environment.properties");
		prop.load(fis);
		
	}
	
	
	public static void initialization() throws MalformedURLException {
		service = AppiumDriverLocalService.buildDefaultService();
		service.start();
		System.out.println("Appium Server Started");
	
			DesiredCapabilities serverCapabilities=new DesiredCapabilities();
            serverCapabilities.setCapability("deviceName",prop.getProperty("deviceID") );            
            serverCapabilities.setCapability("platformName",prop.getProperty("platformName"));
            serverCapabilities.setCapability("VERSION",prop.getProperty("platformVersion"));
            serverCapabilities.setCapability("noReset", prop.getProperty("noReset"));
            serverCapabilities.setCapability("appPackage", prop.getProperty("appPackage"));
            serverCapabilities.setCapability("appActivity",prop.getProperty("appActivity"));
            //serverCapabilities.setCapability("unicodeKeyboard", "true");                                     
            //serverCapabilities.setCapability("resetKeyboard", "true");
            driver=new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), serverCapabilities);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);      
	}
	
	
	public static void shutDown() {

		service.stop();
		System.out.println("Appium Server stopped");
	}
	
	
	public void waitforElementVisibility(MobileElement element) {
		WebDriverWait elementVisisblewait = new  WebDriverWait(driver,5000);
		elementVisisblewait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitforElementClickable(MobileElement element) {
		WebDriverWait elementClicablewait = new  WebDriverWait(driver,5000);
		elementClicablewait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
		
	
	
	public void scroll(double start_xd,double start_yd,double end_xd,double end_yd) throws InterruptedException {
		Dimension dimension =driver.manage().window().getSize();
		
		int start_x = (int)(dimension.width * start_xd);
		int start_y = (int)(dimension.height * start_yd);	
		
		int end_x = (int)(dimension.width * end_xd);
		int end_y = (int)(dimension.height * end_yd);	
		
		
		new TouchAction(driver).press(PointOption.point(start_x,start_y))
							   .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
							   .moveTo(PointOption.point(end_x, end_y)).perform();
		Thread.sleep(3000);
	}
	
	
	public void scrollDown(){
    	new TouchAction(driver).press(PointOption.point(500,800)).waitAction().moveTo(PointOption.point(500, 200)).perform();
	}
	
	
	public void scrolUp(){
    	new TouchAction(driver).press(PointOption.point(500,200)).waitAction().moveTo(PointOption.point(500, 800)).perform();
	}
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	

}
