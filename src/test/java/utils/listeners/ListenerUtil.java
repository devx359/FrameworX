package utils.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;

public class ListenerUtil implements ITestListener 
{
	//AshotUtil ash;
	WebDriver driver;
	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		//System.out.println("onTestStart");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		System.out.println(result.getTestName());
		
		//ash.takeFullScreenShot(driver, "error_");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//System.out.println("onTestSkipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		//System.out.println("on test starrrt");
		//ash=(AshotUtil) context.getAttribute("ashot");
		//driver=(WebDriver) context.getAttribute("driverobj");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//report=(ExtentReports) context.getAttribute("extent");
		//report.flush();
	//	System.out.println("on finish");
		
	}

}
