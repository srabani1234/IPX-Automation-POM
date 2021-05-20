package com.sinch.ipx.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sinch.ipx.base.Testbase;
import com.sinch.ipx.pages.Homepage;
import com.sinch.ipx.pages.LoginPage;
import com.sinch.ipx.utilities.Log;
import com.sinch.ipx.utilities.TestDataProvider;
import com.sinch.ipx.utilities.TestUtil;
public class LoginTest extends Testbase {
	LoginPage loginPa;
	Homepage homepage;
	
	@BeforeMethod (groups = {"Smoke","Sanity","Regression"})
	public void setup() {
		initialization();
		loginPa = new LoginPage();
		
	}
	@AfterMethod (groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().close();
	}

	@Test(testName = "Login Test", description = "This testcase tests the login functionality.",dataProvider = "getHomePageUrlTestData", dataProviderClass = TestDataProvider.class,groups= {"Sanity","Smoke"})
	public void loginTest(Hashtable<String, String> data) throws InterruptedException {
		//LoginPage loginPa = new LoginPage();
		Log.onTestCaseStart("Login test start");
		Log.info("user enter username and password and click on signIn button");
		homepage = loginPa.login(prp.getProperty("userName"), prp.getProperty("passWord"));
		Log.info("user retriver current url");
		String currentURL = Homepage.getCurrentURL();
		Assert.assertEquals(currentURL, data.get("homepageurl"));
		Log.info("Verify homepage title");
		Assert.assertEquals(getDriver().getTitle(), TestUtil.LOGIN_PAGE_TITLE, "Title does not matched(Login Failed)");
		Log.onTestCaseEnd("Login in test end successfulli");
		homepage.logout();
		Thread.sleep(6000);
	}
}
