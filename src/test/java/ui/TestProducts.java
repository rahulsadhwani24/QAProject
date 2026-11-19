package ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.NavLinks;
import pages.PageHome;
import pages.PageProducts;

public class TestProducts extends BaseTest{

	NavLinks nav;
	PageHome home;
	PageProducts productPage;

	@BeforeMethod
	public void pageObjectsInitialization() {
		nav = new NavLinks(driver);
		home = new PageHome(driver);
		productPage = new PageProducts(driver);
	}

	// ------------------------- Products ------------------------- //

	@Test
	public void verifyProductDetails() {
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(home.isHomePageVisible());
		nav.GoToProducts();
		sa.assertTrue(productPage.validateProductPageVisibility());
		productPage.getFirstProductDetails();
		sa.assertTrue(productPage.validateProductInformationVisibility());
		
		sa.assertAll();
	}
	
	
	@Test
	public void validateSearchProducts() {
		nav.GoToProducts();
		String productString = "T-Shirt";
		List<WebElement> products = productPage.validateSearch(productString);
		for(WebElement p: products) {
			Assert.assertTrue(p.findElement(By.cssSelector("div.productinfo p")).getText().contains(productString));
		}
	}
	
	@Test void verifyCategoryProducts() {
		nav.GoToProducts();
		Assert.assertEquals(productPage.categoryWomenDresses(), "WOMEN -  Dress PRODUCTS");
	}
	
	
	
}
