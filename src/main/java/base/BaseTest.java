package base;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ClassUtilities;

public class BaseTest extends ClassUtilities{
	
	public WebDriver driver;
	String browser = System.getProperty("browser") != null ? System.getProperty("browser") : "Chrome";

	public void invokeBrowser() {
		if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox") || browser.equalsIgnoreCase("fire") || browser.equalsIgnoreCase("fox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--incognito");
			driver = new FirefoxDriver(options);
		}
		else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
		}
	}
	
	@BeforeMethod
	public void initializeTest() {
		invokeBrowser();
		if(browser.contains("headless")) {
			driver.manage().window().setSize(new Dimension(1440, 900));
		}else {
			driver.manage().window().maximize();
		}
		driver.get("https://www.automationexercise.com/");
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver != null) {
			driver.quit();
		}
	}
	
}
