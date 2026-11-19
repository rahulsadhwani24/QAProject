package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageHome extends ClassUtilities{
	
	WebDriver driver;
	
	public PageHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "susbscribe_email")
	WebElement inputSubscribeEmail;
	
	@FindBy(id = "subscribe")
	WebElement buttonSubscribe;
	
	@FindBy(className = "alert-success")
	WebElement successMessage;

	@FindBy(id = "slider")
	WebElement slider;
	
	@FindBy(css = "a#scrollUp")
	WebElement scrollTop;
	
	public void subscribe(String email) {
		inputSubscribeEmail.sendKeys(email);
		buttonSubscribe.click();
	}
	
	public boolean validateSubscription() {
		return successMessage.getText().equals("You have been successfully subscribed!");
	}
	
	public boolean isSubscribeVisible() {
		return isElementVisible(driver, buttonSubscribe);
	}
	
	public boolean isHomePageVisible() {
		return isElementVisible(driver, slider);
	}
	
	public void scrollToTop() {
		WaitForElementToAppear(driver, scrollTop);
		scrollTop.click();
	}
	
}
