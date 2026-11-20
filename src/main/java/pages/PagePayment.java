package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PagePayment extends ClassUtilities{

	WebDriver driver;
	
	public PagePayment(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[data-qa='name-on-card']")
	WebElement inputNameOnCard;
	
	@FindBy(css = "input[data-qa='card-number']")
	WebElement inputCardNumber;
	
	@FindBy(css = "input[data-qa='cvc']")
	WebElement inputCVV;

	@FindBy(css = "input[data-qa='expiry-month']")
	WebElement inputExpiryMonth;
	
	@FindBy(css = "input[data-qa='expiry-year']")
	WebElement inputExpiryYear;

	@FindBy(css = "button#submit")
	WebElement buttonPay;
	
	@FindBy(css = "div#success_message div.alert-success")
	WebElement paymentMessage;
	
	@FindBy(css = "h2[data-qa='order-placed']")
	WebElement message;
	
	@FindBy(css = "a.check_out")
	WebElement buttonDownload;
	
	@FindBy(css = "a[data-qa='continue-button']")
	WebElement buttonContinue;
	
	
	public void addCard(String name, String cardNumber, String cvv, String month, String year) {
		WaitForElementToAppear(driver, buttonPay);
		inputNameOnCard.sendKeys(name);
		inputCardNumber.sendKeys(cardNumber);
		inputCVV.sendKeys(cvv);
		inputExpiryMonth.sendKeys(month);
		inputExpiryYear.sendKeys(year);
	}
	
	public void proceedToPay() {
		WaitForElementToAppear(driver, buttonPay);
		buttonPay.click();
	}
	
	public String getStatusMessage() {
		WaitForElementToAppear(driver, message);
//		return paymentMessage.getText().equalsIgnoreCase("Your order has been placed successfully!");
		return message.getText();
	}
	
	public void downloadInvoice() {
		WaitForElementToAppear(driver, buttonDownload);
		buttonDownload.click();
	}
	
	public void clickContinue() {
		WaitForElementToAppear(driver, buttonContinue);
		buttonContinue.click();
	}
	
}
