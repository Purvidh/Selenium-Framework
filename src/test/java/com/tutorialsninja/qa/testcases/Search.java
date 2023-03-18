package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Base.BaseClass;

public class Search extends BaseClass{
	
	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		
		driver = iniliazeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
	
	@Test(priority = 1)
	public void verifySearchwithValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		
		//WebElement msg = driver.findElement(By.linkText("HP LP3065"));
		//Assert.assertTrue(msg.isDisplayed());
		//OR
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
		
		}
	@Test(priority=2)
	public void verifySearchWithInvalidProduct() {
		
		driver.findElement(By.name("search")).sendKeys("Honda");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualMsg, "There is no product that matches the search criteria.","No product in search result is not displayed");
		
	}
	@Test(priority=3)
	public void verifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
		String actualMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualMsg, "There is no product that matches the search criteria.","No product in search result is not displayed");
		
		
		
	}

}
