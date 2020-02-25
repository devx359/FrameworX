package utils.listeners;

import java.lang.reflect.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.Screenshot;

public class TestListener implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("onTestStart");

		Class<? extends ITestResult> testClass = (Class<? extends ITestResult>) result.getInstance().getClass();
		Class<? extends ITestResult> baseTestClass = (Class<? extends ITestResult>) testClass.getSuperclass();

		try {
			Field driverField = baseTestClass.getDeclaredField("reports");
			report = (ExtentReports) driverField.get(result.getInstance());
			test = report.createTest(result.getMethod().getMethodName());
			test.info("DESCRIPTION : " + result.getMethod().getDescription());
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		test.pass(result.getMethod().getMethodName() + " PASSED");
	}

	/*
	 * the driver parameter of Screenshot constructor is provided by the driver()
	 * method;
	 * 
	 * testResult.getName() returns the name of the test script being executed;
	 * 
	 * we pass this as parameter to capture() so that the screenshot is named after
	 * the test script;
	 */
	@Override
	public void onTestFailure(ITestResult testResult) {
		try {
			System.out.println("Inside test failure");
			test.fail(testResult.getMethod().getMethodName() + " FAILED");
			test.log(Status.FAIL,testResult.getThrowable());
			


			// attach screenshot---------------------------
			Screenshot screenshot = new Screenshot(driver(testResult));
			String imagepath = screenshot.capture("listener_" + testResult.getName());

			if (imagepath != null) {
				String path = System.getProperty("user.dir");
				String finalPath = path + imagepath.replace("/", "\\");
				test.addScreenCaptureFromPath(finalPath); //add screenshot to Extent Report
				Reporter.log("<a href='"+ finalPath+ "'> <img src='"+ finalPath + "' height='100' width='100'/> </a>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private WebDriver driver(ITestResult testResult) throws IllegalAccessException {

		try {

			/*
			 * we use reflection and generics to find the driver object:
			 * 
			 * 1. testResult.getInstance() returns the running test class object
			 * 
			 * 2. testResult.getInstance().getClass() returns the class of testResult
			 * (TestClass)
			 * 
			 * 3. testClass.getSuperclass() returns the parent of the test class
			 * (BaseTestClass)
			 * 
			 * 4. Field driverField = baseTestClass.getDeclaredField(“driver”) returns the
			 * driver field of the BaseTestClass
			 * 
			 * 5. driver = (WebDriver)driverField.get(testResult.getInstance()); gets the
			 * value of the driver field from the BaseTestClass
			 */
			Class<? extends ITestResult> testClass = (Class<? extends ITestResult>) testResult.getInstance().getClass();
			Class<? extends ITestResult> baseTestClass = (Class<? extends ITestResult>) testClass.getSuperclass();
			Field driverField = baseTestClass.getDeclaredField("driver");
			WebDriver driver = (WebDriver) driverField.get(testResult.getInstance());

			return driver;
		} catch (SecurityException | NoSuchFieldException | IllegalArgumentException e) {
			throw new RuntimeException("could not get the driver");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart");
		//test=context.getAttribute("extent");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
	}
}
