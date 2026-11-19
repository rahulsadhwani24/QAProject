package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageAccount extends ClassUtilities {
	
	WebDriver driver;
	
	public PageAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[data-qa='signup-name']")
	WebElement inputRegisterName;
	
	@FindBy(css = "input[data-qa='signup-email']")
	WebElement inputRegisterEmail;
	
	@FindBy(css = "button[data-qa='signup-button']")
	WebElement buttonRegister;
	
	@FindBy(css = "input[data-qa='login-email']")
	WebElement inputLoginEmail;
	
	@FindBy(css = "input[data-qa='login-password']")
	WebElement inputLoginPassword;
	
	@FindBy(css = "button[data-qa='login-button']")
	WebElement buttonLogin;

	@FindBy(css = "form[action='/login'] p")
	WebElement loginMessage;
	
	@FindBy(css = "form[action='/signup'] p")
	WebElement signupMessage;
	
	@FindBy(css = "h2[data-qa='account-deleted'] b")
	WebElement accountDeletedMessage;
	
	public void registerUser(String name, String email) {
		WaitForElementToAppear(driver, buttonRegister);
		inputRegisterName.sendKeys(name);
		inputRegisterEmail.sendKeys(email);
		buttonRegister.click();
	}
	
	public void loginUser(String email, String password) {
		WaitForElementToAppear(driver, buttonLogin);
		inputLoginEmail.sendKeys(email);
		inputLoginPassword.sendKeys(password);
		buttonLogin.click();
	}
	
	public boolean validateRegistrationMessage() {
		WaitForElementToAppear(driver, signupMessage);
		return signupMessage.getText().equalsIgnoreCase("Email Address already exist!");
	}
	
	public boolean validateLogin() {
		WaitForElementToAppear(driver, loginMessage);
		return loginMessage.getText().equalsIgnoreCase("Your email or password is incorrect!");
	}
	
	public boolean validateDeleteAccout() {
		WaitForElementToAppear(driver, accountDeletedMessage);
		return accountDeletedMessage.getText().equalsIgnoreCase("Account Deleted!");
	}
	
}
