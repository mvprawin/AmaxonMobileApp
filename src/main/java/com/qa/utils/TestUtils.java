package com.qa.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class TestUtils extends TestBase{

	public TestUtils() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public void waitforElementVisibility(WebElement element) {
		WebDriverWait elementVisisblewait = new  WebDriverWait(driver,5000);
		elementVisisblewait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void waitforElementClickable(WebElement element) {
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
	
	
	

	public static void databaseConnection_SQLServer()
			throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {

		// Load and Register driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// Establish connection
		Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-PCEHKV8\\SQPEXPRESS:1433;databaseName=EmployeeSample;integratedSecurity=true");
		//Connection con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=EmployeeSample;integratedSecurity=true");

		// Create statement
		Statement st = con.createStatement();
		// Execute Statement
		ResultSet rs = st.executeQuery("select FirstName,LastName,SSN,Address,City from dbo.Employee");
		System.out.println("Connected");
		while (rs.next()) {
			String FirstName = rs.getString("FirstName");
			String LastName = rs.getString("LastName");
			String SSN = rs.getString("SSN");
			String Address = rs.getString("Address");
			String City = rs.getString("City");
			
			
			
			
			System.out.println(FirstName+"\t"+LastName+"\t"+SSN+"\t"+Address+"\t"+City);
		}
		// Close Conncetion
		con.close();
	}
	
	public static void databaseConnection_Oracle() throws ClassNotFoundException, SQLException {
		
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/ORCL32", "HR","hr");
		Statement st= con.createStatement();
		ResultSet rs= st.executeQuery("SELECT STREET_ADDRESS,CITY FROM LOCATIONS");
		while(rs.next()) {
			String STREET_ADDRESS = rs.getString("STREET_ADDRESS");
			String CITY = rs.getString("CITY");
			System.out.println(STREET_ADDRESS+"\t"+CITY);
		}
		con.close();
		
	}
	
	

}
