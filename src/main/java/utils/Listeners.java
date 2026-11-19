package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends ClassUtilities implements ITestListener{

	WebDriver driver;
	
	public WebDriver getDriver(ITestResult result) {
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			return driver;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("-----------------Test Started---------------------");
		System.out.println(result.getName());
		if(result.getThrowable() != null) {
			System.out.println(result.getThrowable());
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("-----------------Test Successfully Completed---------------------");
		System.out.println(result.getName());
		if(result.getThrowable() != null) {
			System.out.println(result.getThrowable());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("-----------------Test Failed---------------------");
		captureScreenshot(result.getName(), getDriver(result));
		System.out.println(result.getName());
		if(result.getThrowable() != null) {
			System.out.println(result.getThrowable());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("-----------------Test Failed---------------------");
		captureScreenshot(result.getName(), getDriver(result));
		System.out.println(result.getName());
		if(result.getThrowable() != null) {
			System.out.println(result.getThrowable());
		}
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("-----------------Test Finished---------------------");
	}
}
