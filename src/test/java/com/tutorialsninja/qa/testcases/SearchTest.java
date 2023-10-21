package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.Homepage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	SearchPage searchPage;
	Homepage homepage;
	
	public SearchTest() {
		super();
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homepage = new Homepage(driver);
	}

	@AfterMethod
	public void teardown() {

		driver.quit();
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage = homepage.searchForAProduct(dataProp.getProperty("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct(), "valid product is not displayed in search page");
	}

	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		searchPage = homepage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"abcd", 
				"No product matching the search results");
		//dataProp.getProperty("noProductMessage"),
	}

	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInValidProduct"})
	public void verifySearchWithoutanyProduct() {
		searchPage =  homepage.clickSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(), dataProp.getProperty("noProductMessage"),
				"No product matching the search results");
	}
}
