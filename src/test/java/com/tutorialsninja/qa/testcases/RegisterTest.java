package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	RegisterPage registerPage;

	public RegisterTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		Homepage homePage = new Homepage(driver);
		registerPage = homePage.navigateToRegisterPage();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFields() {
		AccountSuccessPage asp = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		String ActualSuccessHeading = asp.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(ActualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreated"),
				"Account Success Page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {
		AccountSuccessPage asp = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		String ActualSuccessHeading = asp.retrieveAccountSuccessPageHeading();
		Assert.assertEquals(ActualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreated"),
				"Account Success Page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisterWithExistingEmailAdress() {
		registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"),
				prop.getProperty("validEmail"), dataProp.getProperty("telephone"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(
				registerPage.retrieveDuplicateEmailAddressWarning()
						.contains(dataProp.getProperty("duplicateEmailWarning")),
				"warning message regarding duplicate email address is not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisterWithoutFillingAnyDetails() {
		registerPage.clickContinueButton();
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")), "one or more Warning message(s) are not displayed");
	}
}
