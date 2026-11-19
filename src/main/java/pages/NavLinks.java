package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class NavLinks extends ClassUtilities {

	WebDriver driver;
	
	public NavLinks(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Home']")
	WebElement home;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Products']")
	WebElement products;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Cart']")
	WebElement cart;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Signup / Login']")
	WebElement account;

	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Contact us']")
	WebElement contact;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Test Cases']")
	WebElement testCases;
	
	@FindBy(xpath = " //ul[contains(@class, 'navbar-nav')]/li/a[text()=' Logout']")
	WebElement logout;
	
	@FindBy(xpath = "//ul[contains(@class, 'navbar-nav')]/li/a[text()=' Delete Account']")
	WebElement deleteAccount;
	
	public void GoToHome() {
		home.click();
	}
	
	public void GoToProducts() {
		products.click();
	}
	
	public void GoToCart() {
		cart.click();
	}
	
	public void GoToAccount() {
		account.click();
	}
	
	public void GoToContact() {
		contact.click();
	}
	
	public void GoToTestCases() {
		testCases.click();
	}
	
	public void logoutUser() {
		WaitForElementToAppear(driver, logout);
		if(isElementVisible(driver, logout)) {
			logout.click();
		}
	}
	
	public void deleteUserAccount() {
		WaitForElementToAppear(driver, deleteAccount);
		if(isElementVisible(driver, deleteAccount)) {
			deleteAccount.click();
		}
	}
}
