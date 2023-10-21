package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	LoginPage loginPage;

	public LoginTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Homepage homePage = new Homepage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCreds")
	public void VerifyLoginWithValidCreds(String email, String password) {
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.verifyDisplayStatusofAccountInfoOption(),
				"Edit your Account Info option is not displayed");
	}

	@DataProvider(name = "validCreds")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}

	@Test(priority = 2)
	public void VerifyLoginWithInvalidCreds() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

	@Test(priority = 3)
	public void VerifyLoginWithInvalidEmailandValidPassword() {
		loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

	@Test(priority = 4)
	public void VerifyLoginWithValidEmailandInValidPassword() {
		loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

	@Test(priority = 5)
	public void VerifyLoginWithNoEmailandNoPassword() {
		loginPage.clickLoginButton();
		Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessage().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

}
