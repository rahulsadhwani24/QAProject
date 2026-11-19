package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utils.ClassUtilities;

public class PageSignup extends ClassUtilities {

	WebDriver driver;
	
	public PageSignup(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".radio-inline label[for='id_gender1']")
	WebElement radioforMr;
	
	@FindBy(css = ".radio-inline label[for='id_gender2']")
	WebElement radioforMrs;
	
	@FindBy(css = "input[data-qa='name']")
	WebElement inputName;
	
	@FindBy(css = "input[data-qa='password']")
	WebElement inputPassword;
	
	@FindBy(css = "select#days")
	WebElement selectDay;
	
	@FindBy(css = "select#months")
	WebElement selectMonth;
	
	@FindBy(css = "select#years")
	WebElement selectYear;

	@FindBy(css = "label[for='newsletter']")
	WebElement checkboxNewsletter;
	
	@FindBy(css = "label[for='optin']")
	WebElement checkboxSpecialOffers;
	
	@FindBy(css = "input[data-qa='first_name']")
	WebElement inputFirstName;
	
	@FindBy(css = "input[data-qa='last_name']")
	WebElement inputLastName;
	
	@FindBy(css = "input[data-qa='company']")
	WebElement inputCompany;
	
	@FindBy(css = "input[data-qa='address']")
	WebElement inputAddress;
	
	@FindBy(css = "input[data-qa='address2']")
	WebElement inputAddress2;
	
	@FindBy(css = "select#country")
	WebElement selectCountry;
	
	@FindBy(css = "input[data-qa='state']")
	WebElement inputState;
	
	@FindBy(css = "input[data-qa='city']")
	WebElement inputCity;
	
	@FindBy(css = "input[data-qa='zipcode']")
	WebElement inputZipcode;
	
	@FindBy(css = "input[data-qa='mobile_number']")
	WebElement inputNumber;
	
	@FindBy(css = "button[data-qa='create-account']")
	WebElement buttonCreate;
	
	@FindBy(css = "h2[data-qa='account-created']")
	WebElement successMessage;
	
	
	private void setTitle(String title) {
		if(title.equalsIgnoreCase("male") || title.equalsIgnoreCase("mr") || title.equalsIgnoreCase("mr.")) {
			radioforMr.click();
		}
		else {
			radioforMrs.click();
		}
	}
	
	private void setBirthdate(String date) {
		try {
			String[] dateArray = date.trim().split("-");
			
			String day = dateArray[0];
			String month = dateArray[1];
			String year = dateArray[2];
			
			Select select = new Select(selectDay);
			
			select.selectByVisibleText(day);
			select = new Select(selectMonth);
			select.selectByVisibleText(month);
			select = new Select(selectYear);
			select.selectByVisibleText(year);
		}
		catch(Exception e) { }
	}
	
	
	private void setCountry(String string) {
		Select select = new Select(selectCountry);
		select.selectByVisibleText(string);
	}
	
	public void userDetails(String title, String name, String password, String birthdate, String firstName, String lastName, 
			String company, String address,  String country, String state, String city, String zipcode, String number) {
		
		WaitForElementToAppear(driver, inputNumber);
		setTitle(title);
	    inputName.sendKeys(name);
	    inputPassword.sendKeys(password);
	    setBirthdate(birthdate);

	    checkboxSpecialOffers.click();
	    
	    checkboxNewsletter.click();
	    checkboxNewsletter.click();
	    
	    inputFirstName.sendKeys(firstName);
	    inputLastName.sendKeys(lastName);
	    inputCompany.sendKeys(company);
	    inputAddress.sendKeys(address);
	    setCountry(country);
	    inputState.sendKeys(state);
	    inputCity.sendKeys(city);
	    inputZipcode.sendKeys(zipcode);
	    inputNumber.sendKeys(number);
	    
	    buttonCreate.click();
	}
	
	public boolean validateAccountStatus() {
		WaitForElementToAppear(driver, successMessage);
		return successMessage.getText().equalsIgnoreCase("Account Created!");
	}
}
