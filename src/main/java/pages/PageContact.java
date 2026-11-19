package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageContact extends ClassUtilities{
	
	WebDriver driver;
	
	public PageContact(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[data-qa='name']")
	WebElement inputName;
	
	@FindBy(css = "input[data-qa='email']")
	WebElement inputEmail;
	
	@FindBy(css = "input[data-qa='subject']")
	WebElement inputSubject;
	
	@FindBy(css = "input[data-qa='message']")
	WebElement inputMessage;
	
	@FindBy(css = "input[type='file']")
	WebElement inputFile;
	
	@FindBy(css = "input[type='submit']")
	WebElement buttonSubmit;
	
	@FindBy(css = "div.alert-success")
	WebElement successMessage;
	
	public void contactForm(String name, String email, String subject, String...file) {
		inputName.sendKeys(name);
		inputEmail.sendKeys(email);
		inputSubject.sendKeys(subject);
		
		buttonSubmit.click();
		driver.switchTo().alert().accept();
	}
	
	public boolean validateMessage() {
		return successMessage.getText().equals("Success! Your details have been submitted successfully.");
	}
}
