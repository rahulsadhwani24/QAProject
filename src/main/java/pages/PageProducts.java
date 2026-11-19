package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ClassUtilities;

public class PageProducts extends ClassUtilities {

	WebDriver driver;

	public PageProducts(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "h2.title")
	WebElement title;

	@FindBy(className = "product-image-wrapper")
	List<WebElement> products;

	@FindBy(css = "div.choose a")
	WebElement viewProduct;

	@FindBy(id = "search_product")
	WebElement inputSearch;

	@FindBy(id = "submit_search")
	WebElement buttonSearch;

	
	@FindBy(css = "a[href='#Women']")
	WebElement categoryWomen;
	
	@FindBy(css = "div#Women a[href='/category_products/1']")
	WebElement categoryDress;
	
	@FindBy(css = "div#Women a[href='/category_products/2']")
	WebElement categoryTops;
	
	@FindBy(css = "div#Women a[href='/category_products/3']")
	WebElement categorySaree;
	
	
	@FindBy(css = "a[href='#Men']")
	WebElement categoryMen;
	
	@FindBy(css = "div#Men a[href='/category_products/3']")
	WebElement categoryTShirts;
	
	@FindBy(css = "div#Men a[href='/category_products/6']")
	WebElement categoryJeans;
	
	
	@FindBy(css = "a[href='#Kids']")
	WebElement categoryKids;
	
	@FindBy(css = "div#Men a[href='/category_products/4']")
	WebElement categoryKidsDress;
	
	@FindBy(css = "div#Men a[href='/category_products/5']")
	WebElement categoryKidsShirts;
	
	
	// Product Description Page
	@FindBy(className = "product-information")
	WebElement productInformation;

	// Product Description Page
	@FindBy(css = "div.productinfo p")
	By productDescription;

	// Product Description Page
	@FindBy(css = "input#quantity")
	WebElement inputQuantity;

	// Product Description Page
	@FindBy(css = "button.cart")
	WebElement buttonCart;

	// Overlay after add product to cart
	@FindBy(css = "button[data-dismiss='modal']")
	WebElement buttonContinue;

	// Overlay after add product to cart
	@FindBy(xpath = "//a/u[text()='View Cart']")
	WebElement linkToCart;

	public List<WebElement> validateSearch(String search) {
		inputSearch.sendKeys(search);
		buttonSearch.click();
		WaitForElementToAppear(driver, viewProduct);
		return products;
	}

	public List<WebElement> getAllProducts() {
		WaitForElementToAppear(driver, viewProduct);
		return products;
	}

	public int getProductsCount() {
		WaitForElementToAppear(driver, viewProduct);
		return products.size();
	}

	public boolean validateProductPageVisibility() {
		WaitForElementToAppear(driver, title);
		return title.getText().equalsIgnoreCase("All Products");
	}

	public void addProductToCartAndContinue(int index) {
		if (index > 40 || index < 1) {
			System.out.println("You can select index from 1 to 40");
		}
		WaitForElementToAppear(driver, viewProduct);
		driver.findElement(By.cssSelector("div.productinfo a.add-to-cart[data-product-id='"+index+"']")).click();
		WaitForElementToAppear(driver, buttonContinue);
		buttonContinue.click();
	}

	public void addProductToCartAndViewCart(int index) {
		if (index > 40 || index < 1) {
			System.out.println("You can select index from 1 to 40");
		}
		WaitForElementToAppear(driver, viewProduct);
		driver.findElement(By.cssSelector("div.productinfo a.add-to-cart[data-product-id='"+index+"']")).click();
		WaitForElementToAppear(driver, linkToCart);
		linkToCart.click();
	}

	public void getFirstProductDetails() {
		WaitForElementToAppear(driver, viewProduct);
		viewProduct.click();
	}

	public boolean validateProductInformationVisibility() {
		return isElementVisible(driver, productInformation);
	}

	public void getProductDetails(int index) {
		if (index > 40 || index < 1) {
			System.out.println("You can select index from 1 to 40");
		}
		WaitForElementToAppear(driver, viewProduct);
		driver.findElement(By.cssSelector("div.choose a[href='/product_details/"+index+"']")).click();
	}

	public void addProductToCartWithQuantity(String quantity) {
		WaitForElementToAppear(driver, inputQuantity);
		inputQuantity.clear();
		inputQuantity.sendKeys(quantity);
		buttonCart.click();
		WaitForElementToAppear(driver, linkToCart);
		linkToCart.click();
	}
	
	public String categoryWomenDresses() {
		WaitForElementToAppear(driver, categoryWomen);
		categoryWomen.click();
		WaitForElementToAppear(driver, categoryDress);
		categoryDress.click();
		WaitForElementToAppear(driver, title);
		return title.getText();
	}

}
