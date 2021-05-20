package com.sinch.ipx.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sinch.ipx.actiondriver.Action;
import com.sinch.ipx.base.Testbase;
import com.sinch.ipx.locator.HomePage;
import com.sinch.ipx.locator.Login;
import com.sinch.ipx.utilities.TestUtil;

public class LoginPage extends Testbase {
	
	
	@FindBy(id = Login.LoginLocator.LOGIN_USERNAME)
	WebElement usernameField;

	@FindBy(id = Login.LoginLocator.LOGIN_PASSWORD)
	WebElement password;

	@FindBy(id = Login.LoginLocator.LOGIN_BUTTON)
	WebElement logInBtn;
	
	@FindBy(id = HomePage.Homepage.ADD_CUSTOMER)
	WebElement addcustomer;

	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
		
	}
	
	public  Homepage login(String strUserName, String strPassword) {
		Action.type(usernameField, strUserName);
		Action.type(password,strPassword );
		Action.click(getDriver(), logInBtn);
		Action.explicitWait(getDriver(),addcustomer,TestUtil.EXPLICIT_WAIT);
		return new Homepage();
	}

}
