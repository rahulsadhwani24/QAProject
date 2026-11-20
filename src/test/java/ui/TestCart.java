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
import pages.PageAccount;
import pages.PageCart;
import pages.PageHome;
import pages.PageProducts;

public class TestCart extends BaseTest {

	NavLinks nav;
	PageHome home;
	PageProducts productPage;
	PageCart cart;

	@BeforeMethod
	public void pageObjectsInitialization() {
		nav = new NavLinks(driver);
		home = new PageHome(driver);
		productPage = new PageProducts(driver);
		cart = new PageCart(driver);
	}

	@Test(groups = "cart")
	public void addProductsToCart() {
		nav.GoToProducts();
		int arr[] = { 1, 2 };
		for (int i : arr) {
			productPage.addProductToCartAndContinue(i);
		}
		nav.GoToCart();
		Assert.assertEquals(cart.cartProductCount(), 2);
	}

	@Test(groups = "cart")
	public void addProductToCartWithIncreaseQuantityAndValidate() {
		String quantity = "4";
		nav.GoToProducts();
		productPage.getProductDetails(3);
		productPage.addProductToCartWithQuantity(quantity);
		nav.GoToCart();
		Assert.assertEquals(cart.verifyProductQuantity(), quantity);
	}

	@Test(groups = "cart")
	public void validateDeleteProductsFromCart() throws InterruptedException {
		nav.GoToProducts();
		int arr[] = { 1, 2 };
		for (int i : arr) {
			productPage.addProductToCartAndContinue(i);
		}
		nav.GoToCart();
		cart.deleteProductFromCart(arr[1]);
		cart.deleteProductFromCart(arr[0]);
		Thread.sleep(4000);
	}

	@Test(groups = "cart")
	public void validateCartAfterLogin() {
		SoftAssert sa = new SoftAssert();
		nav.GoToProducts();
		
		String productString = "T-Shirt";
		List<WebElement> products = productPage.validateSearch(productString);
		for (WebElement p : products) {
			int getId = Integer
					.parseInt(p.findElement(By.cssSelector("a.add-to-cart")).getAttribute("data-product-id"));
			productPage.addProductToCartAndContinue(getId);
		}
		
		nav.GoToCart();
		int getCount = cart.cartProductCount();
		
		nav.GoToAccount();
		PageAccount acc = new PageAccount(driver);
		acc.loginUser(readProperty("email"), readProperty("password"));
		sa.assertTrue(nav.isUserLoggedIn());
		
		nav.GoToCart();
		sa.assertEquals(cart.cartProductCount(), getCount);
		
		sa.assertAll();
	}

	
	@Test(groups = "cart")
	public void addProductToCartFromRecommendedItems() {
		productPage.addProductToCartAndContinue(8);
		nav.GoToCart();
		Assert.assertEquals(cart.cartProductCount(), 1);
	}
	
}
