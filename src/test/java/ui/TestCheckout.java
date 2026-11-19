package ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.NavLinks;
import pages.PageAccount;
import pages.PageCart;
import pages.PageCheckout;
import pages.PagePayment;
import pages.PageProducts;
import pages.PageSignup;
import utils.DataProviders;

public class TestCheckout extends BaseTest{

	NavLinks nav;
	PageAccount account;
	PageProducts productPage;
	PageCart cart;
	PageCheckout checkOut;
	PagePayment payment;
	SoftAssert sa;

	PageSignup signup;

	@BeforeMethod
	public void pageObjectsInitialization() {
		nav = new NavLinks(driver);
		account = new PageAccount(driver);
		signup = new PageSignup(driver);
		productPage = new PageProducts(driver);
		cart = new PageCart(driver);
		checkOut = new PageCheckout(driver);
		payment = new PagePayment(driver);

		sa = new SoftAssert();
	}
	
	@Test
	public void loginWhileCheckout() {
		productPage.addProductToCartAndContinue(2);
		productPage.addProductToCartAndContinue(4);
		nav.GoToCart();
		cart.proceedToCheckoutAndGoToAccountPage();
		account.loginUser(readProperty("email"), readProperty("password"));
		sa.assertTrue(nav.isUserLoggedIn());
		
		nav.GoToCart();
		cart.proceedToCheckout();
		checkOut.placeOrder();
		payment.addCard("Rahul Sadhwani", "123456789012", "123", "12", "2040");
		payment.proceedToPay();
		sa.assertEquals(payment.getStatusMessage(), "ORDER PLACED!");
		
		sa.assertAll();
	}
	
	@Test
	public void loginBeforeCheckout() {
		nav.GoToAccount();
		account.loginUser(readProperty("email"), readProperty("password"));
		sa.assertTrue(nav.isUserLoggedIn());
		
		nav.GoToProducts();
		productPage.addProductToCartAndContinue(2);
		productPage.addProductToCartAndContinue(4);
		nav.GoToCart();
		cart.proceedToCheckout();
		checkOut.placeOrder();
		payment.addCard("Rahul Sadhwani", "123456789012", "123", "12", "2040");
		payment.proceedToPay();
		sa.assertEquals(payment.getStatusMessage(), "ORDER PLACED!");
		
		sa.assertAll();
	}
	
	
	@Test(dataProvider = "singleUserData", dataProviderClass = DataProviders.class)
	public void registerWhileCheckoutAndDeleteAccount(String title, String firstName, String lastName, String birthdate, String password, 
			String company, String address,  String country, String state, String city, String zipcode, String number, String email) {

		String name = firstName + lastName;
		email = email.replace("@", System.currentTimeMillis() + "@");
		
		productPage.addProductToCartAndContinue(2);
		productPage.addProductToCartAndContinue(4);
		nav.GoToCart();
		cart.proceedToCheckoutAndGoToAccountPage();
		account.registerUser(name, email);
		signup.userDetails(title, name, password, birthdate, firstName, lastName, company, address, country, state, city, zipcode, number);
		sa.assertTrue(signup.validateAccountStatus());
		
		nav.GoToCart();
		cart.proceedToCheckout();
		checkOut.placeOrder();
		payment.addCard("Rahul Sadhwani", "123456789012", "123", "12", "2040");
		payment.proceedToPay();
		sa.assertEquals(payment.getStatusMessage(), "ORDER PLACED!");
		
		nav.deleteUserAccount();
		sa.assertTrue(account.validateDeleteAccout());
		
		sa.assertAll();	
	}
	
	@Test(dataProvider = "singleUserData", dataProviderClass = DataProviders.class)
	public void registerBeforeCheckoutAndDeleteAccount(String title, String firstName, String lastName, String birthdate, String password, 
			String company, String address,  String country, String state, String city, String zipcode, String number, String email) {
		
		String name = firstName + lastName;
		email = email.replace("@", System.currentTimeMillis() + "@");
		
		nav.GoToAccount();
		account.registerUser(name, email);
		signup.userDetails(title, name, password, birthdate, firstName, lastName, company, address, country, state, city, zipcode, number);
		sa.assertTrue(signup.validateAccountStatus());
		
		nav.GoToProducts();
		productPage.addProductToCartAndContinue(2);
		productPage.addProductToCartAndContinue(4);
		nav.GoToCart();
		cart.proceedToCheckout();
		checkOut.placeOrder();
		payment.addCard("Rahul Sadhwani", "123456789012", "123", "12", "2040");
		payment.proceedToPay();
		sa.assertEquals(payment.getStatusMessage(), "ORDER PLACED!");
		
		nav.deleteUserAccount();
		sa.assertTrue(account.validateDeleteAccout());
		
		sa.assertAll();	
	}
}
