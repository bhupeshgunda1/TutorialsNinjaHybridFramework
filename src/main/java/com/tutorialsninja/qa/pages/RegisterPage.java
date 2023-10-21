package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id = "input-password")
	private WebElement passwordField;

	@FindBy(id = "input-confirm")
	private WebElement passwordConfirmField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyField;

	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement yesNewsLetterOption;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarningField;

	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarningField;

	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarningField;

	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarningField;

	@FindBy(xpath = "//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarningField;

	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarningField;

	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarningField;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Actions
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephone(String TelephoneText) {
		telephoneField.sendKeys(TelephoneText);
	}

	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordText) {
		passwordConfirmField.sendKeys(confirmPasswordText);
	}

	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}

	public AccountSuccessPage clickContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void yesNewsLetterOption() {
		yesNewsLetterOption.click();
	}

	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarningText = duplicateEmailAddressWarningField.getText();
		return duplicateEmailAddressWarningText;
	}

	public String retrievePrivacyPolicyWarning() {
		String PrivacyPolicyWarningText = privacyPolicyWarningField.getText();
		return PrivacyPolicyWarningText;
	}

	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstNameWarningField.getText();
		return firstNameWarningText;
	}

	public String retrievelastNameWarning() {
		String lastNameWarningText = lastNameWarningField.getText();
		return lastNameWarningText;
	}

	public String retrieveemailWarning() {
		String emailWarningText = emailWarningField.getText();
		return emailWarningText;
	}

	public String retrieveTelephoneWarning() {
		String telephoneWarningText = telephoneWarningField.getText();
		return telephoneWarningText;
	}

	public String retrievePasswordWarning() {
		String passwordWarningText = passwordWarningField.getText();
		return passwordWarningText;
	}

	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText,
			String TelephoneText, String passwordText, String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(TelephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(confirmPasswordText);
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText,
			String TelephoneText, String passwordText, String confirmPasswordText) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(TelephoneText);
		passwordField.sendKeys(passwordText);
		passwordConfirmField.sendKeys(confirmPasswordText);
		yesNewsLetterOption.click();
		privacyPolicyField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
			String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning,
			String expectedPasswordWarning) {

		boolean privacyPolicyWarningStatus = privacyPolicyWarningField.getText().contains(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = firstNameWarningField.getText().equals(expectedFirstNameWarning);
		boolean lastNameWarningStatus = lastNameWarningField.getText().equals(expectedLastNameWarning);
		boolean emailWarningStatus = emailWarningField.getText().equals(expectedEmailWarning);
		boolean telephoneWarningStatus = telephoneWarningField.getText().equals(expectedTelephoneWarning);
		boolean passwordWarningStatus = passwordWarningField.getText().equals(expectedPasswordWarning);

		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus
				&& telephoneWarningStatus && passwordWarningStatus;
	}
}
