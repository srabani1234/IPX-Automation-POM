package com.sinch.ipx.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.sinch.ipx.actiondriver.Action;
import com.sinch.ipx.utilities.ExtentReportManager;
import com.sinch.ipx.utilities.Mailing;
import com.sinch.ipx.utilities.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testbase {

	public static Properties prp;
	// public static WebDriver driver;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	
	public static WebDriver getDriver() {
		// get driver from local driver
		return driver.get();
		
	}
	
	@BeforeSuite (groups = { "Smoke", "Sanity", "Regression" })
	public void beforeSuite() {
		loadConfig();
		ExtentReportManager.extentReportSetUp(prp.getProperty("browser"));
		DOMConfigurator.configure("log4j.xml");
	}
//	@BeforeTest (groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		
		try {
			prp = new Properties();
			FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"\\configuration\\config.properties");
			prp.load(fi);
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	protected static void initialization() {
		String browserName = prp.getProperty("browser").toLowerCase();
		if(TestUtil.CHROME_BROWSER.equalsIgnoreCase(browserName)) {
			WebDriverManager.chromedriver().setup();
			// set browserr to threadLocalMap
			driver.set(new ChromeDriver());
			
		}
		else if(TestUtil.FIREFOX_BROWSER.equalsIgnoreCase(browserName)) {
		
	    	WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			
		}
		
	
		getDriver().manage().window().maximize();
		Action.implicitWait(getDriver(), TestUtil.IMPLICIT_WAIT);
		Action.pageLoadTimeOut(getDriver(), TestUtil.PAGE_LOAD_TIMEOUT);
		getDriver().get(prp.getProperty("url"));
		
	}
	@AfterSuite
	public void endSetup() throws AddressException, IOException, MessagingException{
		ExtentReportManager.endReport();
		

		Runtime r=Runtime.getRuntime();  	  
		r.addShutdownHook(new Thread(){  
		
			public void run(){  
			Mailing sm = new Mailing();
			try {
				sm.mail();
				System.out.println("Report has been sent"); 
			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    }  
		}  
		);  
		try{Thread.sleep(5000);}catch (Exception e) 
		{
			System.out.println(e);
		}  
	}
	
	
}
