package base;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
			options.addArguments("--headless");
			driver = new FirefoxDriver(options);
		}
		else {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			
			// To ByPass Ads
			Map<String, Object> params = new HashMap<>();
			params.put("urls", Arrays.asList(
			    "*doubleclick.net*",
			    "*googlesyndication.com*",
			    "*googleadservices.com*",
			    "*adservice.google.com*",
			    "*pagead2.googlesyndication.com*"
			));
			
			((ChromeDriver) driver).executeCdpCommand("Network.enable", new HashMap<>());
			((ChromeDriver) driver).executeCdpCommand("Network.setBlockedURLs", params);
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void initializeTest() {
		invokeBrowser();
		driver.manage().window().maximize();
		driver.get(readProperty("url"));
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}
	
}
