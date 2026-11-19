package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.NavLinks;
import pages.PageCart;
import pages.PageContact;
import pages.PageHome;

public class TestGeneral extends BaseTest {

	NavLinks nav;
	PageHome home;

	@BeforeMethod
	public void pageObjectInitialization() {
		nav = new NavLinks(driver);
		home = new PageHome(driver);
	}

	// ------------------------- Navigation ------------------------- //

	@Test
	public void verifyTestCasesPage() {
		nav.GoToTestCases();
		String title = driver.findElement(By.cssSelector("h2.title b")).getText();
		Assert.assertEquals(title, "TEST CASES");
	}

	// ------------------------- Contact ------------------------- //

	@Test
	public void validateContactForm() {
		SoftAssert softAssert = new SoftAssert();
		PageContact contact = new PageContact(driver);
		nav.GoToContact();
		contact.contactForm("Rahul", "rahu@gmail.com", "Validating Contact Page");
		softAssert.assertTrue(contact.validateMessage());
		nav.GoToHome();
		softAssert.assertAll();
	}

	// ------------------------- Subscription ------------------------- //

	@Test
	public void verifySubscriptionInHomePage() {
		
		home.subscribe("rahul@gmail.com");
		Assert.assertTrue(home.validateSubscription());
	}
	
	
	@Test
	public void verifySubscriptionInCartPage() {
		nav.GoToCart();
		PageCart cart = new PageCart(driver);
		cart.subscribe("rahul@gmail.com");
		Assert.assertTrue(cart.validateSubscription());
	}
	
	@Test
	public void verifyScrollWithButton() {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(home.isHomePageVisible());
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(home.isSubscribeVisible()) {
			home.scrollToTop();
			sa.assertTrue(home.isHomePageVisible());
		}
		sa.assertAll();
	}
	
	@Test
	public void verifyScrollWithoutButton() {
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(home.isHomePageVisible());
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sa.assertTrue(home.isSubscribeVisible());
		
		jse.executeScript("window.scrollTo(0, 0)");
		sa.assertTrue(home.isHomePageVisible());
		
		sa.assertAll();
	}
}
