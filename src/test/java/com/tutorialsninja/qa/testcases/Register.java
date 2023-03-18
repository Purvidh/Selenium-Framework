package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.BaseClass;
import com.tutorialsninja.qa.utis.Utilities;

public class Register extends BaseClass{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
//	String browserName="chrome";
//	
//	if(browserName.equals("chrome")) {
//		driver = new ChromeDriver();
//	}
//	else if (browserName.equals("firefox")){
//		driver = new FirefoxDriver();	
//	}
//	else if (browserName.equals("edge")){
//		driver = new EdgeDriver();
//	}
//	driver.manage().window().maximize();
//	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//	driver.get("http://www.tutorialsninja.com/demo/");

	loadConfig();
	driver = iniliazeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	driver.findElement(By.linkText("My Account")).click();
	driver.findElement(By.linkText("Register")).click();
	
	}
	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}
		
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Purvi");
		driver.findElement(By.id("input-lastname")).sendKeys("Shah");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success page is not displayed");
		
			
		
	}
	@Test(priority=2)
public void verifyRegisteringAndAccountByProvidingAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Purvi");
		driver.findElement(By.id("input-lastname")).sendKeys("Shah");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("123456");
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualSuccessHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading, "Your Account Has Been Created!", "Account Success page is not displayed");
		
			
		
	}
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {
			
			driver.findElement(By.id("input-firstname")).sendKeys("Purvi");
			driver.findElement(By.id("input-lastname")).sendKeys("Shah");
			driver.findElement(By.id("input-email")).sendKeys("purvi0406@yahoo.com");
			driver.findElement(By.id("input-telephone")).sendKeys("123456");
			driver.findElement(By.id("input-password")).sendKeys("123456");
			driver.findElement(By.id("input-confirm")).sendKeys("123456");
			driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']"));
			driver.findElement(By.name("agree")).click();
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			
			String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
			Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message regarding duplicate email address is not displayed");
			
				
			
		}
	@Test(priority=4)
	public void verifyregisteringAccountWithoutFillingAnyDetails() {
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"), "Privacy Policy warning message is not displayed");
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarning,  "First Name must be between 1 and 32 characters!", "First name warning message is not displayed");
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarning,  "Last Name must be between 1 and 32 characters!", "last name warning message is not displayed");
		
		String actualEmailAddWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailAddWarning,  "E-Mail Address does not appear to be valid!", "Email  warning message is not displayed");
		
		String actualTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualTelephoneWarning,  "Telephone must be between 3 and 32 characters!", "Telephone  warning message is not displayed");
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarning,  "Password must be between 4 and 20 characters!", "Password  warning message is not displayed");
		
	}
	
		
	}
	


