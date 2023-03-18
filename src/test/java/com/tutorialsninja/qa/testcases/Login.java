package com.tutorialsninja.qa.testcases;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.BaseClass;
import com.tutorialsninja.qa.utis.Utilities;



public class Login extends BaseClass {
	WebDriver driver;
	
	
	@BeforeMethod
	public void setup() {
//		String browserName = "chrome";
//		if(browserName.equals("chrome"))
//		{
//			driver = new ChromeDriver();
//		}
//		else if(browserName.equals("firefox"))
//		{
//			driver = new FirefoxDriver();
//		}
//		else if(browserName.equals("edge"))
//		{
//			driver = new EdgeDriver();
//		}
//		driver.manage().window().maximize(); driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));	driver.get("http://www.tutorialsninja.com/demo/");
		
		driver = iniliazeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}
	
	@Test(priority=1)
	public void verifyLoginWithValidCredentials()
	{
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmailAddress"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		
			
	}
	@Test(priority =2, invocationCount=3)
	public void verifyLoginWithInvalidCredentials()
	{
		
		//driver.findElement(By.id("input-email")).sendKeys("purvi0406"+generteTimeStamp()+"@yahoo.com"); Before creation of Utilities class
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("Aa1Bb2Cc3Dd4678");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedwarningMessage, "Messages are not equal");
		Assert.assertTrue(actualWarningMessage.contains(expectedwarningMessage), "Messages are not equal");
		
		}
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{
		
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertEquals(actualWarningMessage, expectedwarningMessage, "Messages are not equal");
		//Assert.assertTrue(actualWarningMessage.contains(expectedwarningMessage), "Messages are not equal");
		
			
	}
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmailAddress"));
		driver.findElement(By.id("input-password")).sendKeys("Aa1Bb2Cc3Dd4567");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		//Assert.assertEquals(actualWarningMessage, expectedwarningMessage, "Messages are not equal");
		Assert.assertTrue(actualWarningMessage.contains(expectedwarningMessage), "Messages are not equal");
		
	}
	@Test(priority=5)
	public void LoginwithoutCredentials() {
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedwarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		//Assert.assertEquals(actualWarningMessage, expectedwarningMessage, "Messages are not equal");
		Assert.assertTrue(actualWarningMessage.contains(expectedwarningMessage), "Messages are not equal");
		
		
		
		
		
		
	}
	
}
