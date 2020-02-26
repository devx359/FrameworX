package utils.listeners;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;
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
	Logger log;

	@Override
	public void onTestStart(ITestResult result) {
		log.debug("onTestStart");

		Class<? extends ITestResult> testClass = (Class<? extends ITestResult>) result.getInstance().getClass();
		Class<? extends ITestResult> baseTestClass = (Class<? extends ITestResult>) testClass.getSuperclass();

		try {
			Field driverField = baseTestClass.getDeclaredField("reports");
			report = (ExtentReports) driverField.get(result.getInstance());
			test = report.createTest(result.getMethod().getMethodName());
			test.info("DESCRIPTION : " + result.getMethod().getDescription());

			// set logs
			log.info("------------------------------" + result.getMethod().getMethodName()
					+ "----------------------------------------------");
			log.info("DESCRIPTION : " + result.getMethod().getDescription());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log.debug("onTestSuccess");
		test.pass(result.getMethod().getMethodName() + " PASSED");
		log.info(result.getMethod().getMethodName() + " PASSED");
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
			log.debug("Inside onTestFailure");
			test.fail(testResult.getMethod().getMethodName() + " FAILED");
			test.log(Status.FAIL, testResult.getThrowable()); // show stacktrace in extent

			log.error("METHOD " + testResult.getMethod().getMethodName().toString() + " FAILED");
			log.error(testResult.getThrowable().toString());

			// attach screenshot---------------------------
			Screenshot screenshot = new Screenshot(driver(testResult));
			String imagepath = screenshot.capture("listener_" + testResult.getName());

			if (imagepath != null) {
				String path = System.getProperty("user.dir");
				String finalPath = path + imagepath.replace("/", "\\");
				log.info("Screenshot Saved to : " + finalPath);
				test.addScreenCaptureFromPath(finalPath); // add screenshot to Extent Report
				Reporter.log("<a href='" + finalPath + "'> <img src='" + finalPath + "' height='100' width='100'/> </a>"); //add screenshot to testng report
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
		log.debug("onTestSkipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		
		log = Logger.getLogger("devpinoyLogger");
		context.setAttribute("logger", log);
		// System.out.println("onStart");
		log.debug("onStart");
		log.info("Test execution started");
		// test=context.getAttribute("extent");
	}

	@Override
	public void onFinish(ITestContext context) {
		// System.out.println("onFinish");
		log.debug("Test Execution Finished");
	}
}
