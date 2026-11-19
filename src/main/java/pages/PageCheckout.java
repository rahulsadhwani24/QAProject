package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageCheckout extends ClassUtilities{

	WebDriver driver;
	public PageCheckout(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "#ordermsg textarea")
	WebElement inputDescription;
	
	@FindBy(css = "a[href='/payment']")
	WebElement buttonPlaceOrder;
	
	
	public void placeOrderWithDescription(String message) {
		WaitForElementToAppear(driver, buttonPlaceOrder);
		inputDescription.sendKeys(message);
		buttonPlaceOrder.click();
	}
	
	public void placeOrder() {
		WaitForElementToAppear(driver, buttonPlaceOrder);
		buttonPlaceOrder.click();
	}
	
}
