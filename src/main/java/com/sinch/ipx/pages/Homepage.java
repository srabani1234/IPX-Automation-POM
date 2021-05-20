package com.sinch.ipx.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sinch.ipx.actiondriver.Action;
import com.sinch.ipx.base.Testbase;
import com.sinch.ipx.locator.HomePage;
import com.sinch.ipx.utilities.TestUtil;

public class Homepage extends Testbase {

	@FindBy(xpath = HomePage.Homepage.LOGOUT_BUTTON)
	WebElement logoutBtn;



	public Homepage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	public static String getCurrentURL() {
		String currentURL= getDriver().getCurrentUrl();
		return currentURL;
	}
	
	public void logout() {
	
		Action.explicitWait(getDriver(),logoutBtn,TestUtil.EXPLICIT_WAIT);
		Action.click(getDriver(), logoutBtn);
		
		
	}
}
