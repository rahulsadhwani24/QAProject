package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageCart extends ClassUtilities{

	WebDriver driver;
	
	public PageCart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "susbscribe_email")
	WebElement inputSubscribeEmail;
	
	@FindBy(id = "subscribe")
	WebElement buttonSubscribe;
	
	@FindBy(className = "alert-success")
	WebElement successMessage;

	@FindBy(css = "table#cart_info_table")
	WebElement cartTable;
	
	@FindBy(css = "#cart_info_table tbody tr")
	List<WebElement> cartProducts; 
	
	@FindBy(css = "tbody td.cart_quantity")
	WebElement productQuantity;
	
	@FindBy(css = "a.check_out")
	WebElement buttonCheckout;
	
	@FindBy(css = "div.modal-body a")
	WebElement buttonAccount;
	
	
	public void subscribe(String email) {
		inputSubscribeEmail.sendKeys(email);
		buttonSubscribe.click();
	}
	
	public boolean validateSubscription() {
		return successMessage.getText().equals("You have been successfully subscribed!");
	}

	public int cartProductCount() {
		WaitForElementToAppear(driver, cartTable);
		return cartProducts.size();
	}
	
	public String verifyProductQuantity() {
		WaitForElementToAppear(driver, cartTable);
		return productQuantity.getText();
	}
	
	public boolean validateCartPageDisplayed() {
		return isElementVisible(driver, buttonSubscribe);
	}
	
	public void deleteProductFromCart(int id) {
		WaitForElementToAppear(driver, cartTable);
		driver.findElement(By.cssSelector("tr#product-"+id+" td.cart_delete a[data-product-id='"+id+"']")).click();
	}
	
	public void proceedToCheckoutAndGoToAccountPage() {
		WaitForElementToAppear(driver, buttonCheckout);
		buttonCheckout.click();
		WaitForElementToAppear(driver, buttonAccount);
		buttonAccount.click();
	}	
	
	public void proceedToCheckout() {
		WaitForElementToAppear(driver, buttonCheckout);
		buttonCheckout.click();
	}
}
