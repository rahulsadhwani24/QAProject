package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{

	int count = 0;
	int retries = 2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		while(count < retries) {
			if(result.isSuccess()) {
				return true;
			}
			count++;
		}
		return false;
	}
}
