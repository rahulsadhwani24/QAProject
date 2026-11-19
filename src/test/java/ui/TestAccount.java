package ui;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.NavLinks;
import pages.PageAccount;
import pages.PageSignup;
import utils.DataProviders;

public class TestAccount extends BaseTest {

	PageAccount account;
	NavLinks nav;

	@BeforeMethod
	public void accountPageInitialization() {
		account = new PageAccount(driver);
		nav = new NavLinks(driver);
		nav.GoToAccount();
	}

	// ----------------------- Registration ----------------------- //

	@Test(groups = "Registration")
	public void registerUserWithExistingEmail() {
		account.registerUser("rahul", readProperty("email"));
		Assert.assertTrue(account.validateRegistrationMessage());
	}

	@Test(groups = "Registration", dataProvider = "singleUserData", dataProviderClass = DataProviders.class)
	public void registerUser(String title, String firstName, String lastName, String birthdate, String password,
			String company, String address, String country, String state, String city, String zipcode, String number,
			String email) {

		String name = firstName + lastName;
		account.registerUser(name, email);

		PageSignup signup = new PageSignup(driver);
		signup.userDetails(title, name, password, birthdate, firstName, lastName, company, address, country, state,
				city, zipcode, number);
		Assert.assertTrue(signup.validateAccountStatus());
	}

	// ----------------------- Login ----------------------- //

	@Test(dependsOnMethods = "registerUser", groups = "Login")
	public void validateLoginWithCorrectDetails() {
		account.loginUser(readProperty("email"), readProperty("password"));
		Assert.assertTrue(nav.isUserLoggedIn());
	}

	@Test(groups = "Login")
	public void validateLoginWithIncorrectDetails() {
		account.loginUser("abc@gmail.com", "abc123");
		Assert.assertTrue(account.validateLogin());
	}

	@Test(groups = "Login")
	public void logoutUser() {
		account.loginUser(readProperty("email"), readProperty("password"));
		if (nav.isUserLoggedIn()) {
			nav.logoutUser();
		}
	}
}
