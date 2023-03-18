package com.tutorialsninja.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import com.tutorialsninja.qa.utis.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class BaseClass {

	public static Properties prop;
	public static WebDriver driver;
		
	
	public void loadConfig() {
		
		
		try {
		      prop = new Properties();
			  System.out.println("Super constructor invoked");
			  FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");	
			   prop.load(ip);
				System.out.println("driver: " + driver);
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			  	
	}
	
	
	public WebDriver iniliazeBrowserAndOpenApplicationURL(String browserName) {
		
		WebDriverManager.chromedriver().setup();
		String browserName1 = prop.getProperty("browserName");
		if(browserName1.equals("chrome")) {
			
			driver = new ChromeDriver();
			
		}
		
		else if(browserName1.equals("firefox")) {
			
			driver = new FirefoxDriver();
		}
		else if(browserName1.equals("edge")){
			driver = new EdgeDriver();	
			
		}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.Implicit_Wait_Time));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PageLoad_Time));
			driver.get(prop.getProperty("url"));
			
			return driver;
	}
}
	
	
	