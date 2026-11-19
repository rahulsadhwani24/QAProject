package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClassUtilities {

	public void WaitForElementToAppear(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public void WaitForElementToAppear(WebDriver driver, By byLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	
	public boolean isElementVisible(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement element = wait.until(ExpectedConditions.visibilityOf(webElement));
		return element.isDisplayed();
	}
	
	public String readProperty(String propName) {
		Properties prop = new Properties();
		try {
			FileInputStream fin = new FileInputStream("./src/main/resources/config.properties");
			prop.load(fin);
			return prop.getProperty(propName);
		}
		catch(Exception e) { 
			return null;
		}
	}
	
	public void captureScreenshot(String screenshotName, WebDriver driver) {
		if(driver == null) {
			System.out.println("Cannot capture screenshot | Driver is null");
			return;
		}
		TakesScreenshot ss = (TakesScreenshot) driver;
		File source = ss.getScreenshotAs(OutputType.FILE);
	    File destination = new File("./test-output/screenshots/" + screenshotName + ".png");
	    try {
	        FileUtils.copyFile(source, destination);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
