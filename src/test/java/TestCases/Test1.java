package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import userDefinedFunctions.Link;
import utils.DriverUtil;
import utils.ExtentManager;

public class Test1 {
	WebDriver driver;
	Link link;
	ExtentReports reports;
	ExtentTest test;
	ExtentManager ExtentManagerObj;

	@BeforeClass
	public void setup(ITestContext context) {
		driver = new DriverUtil().DriverSetup("chrome");
		link = new Link(driver, context);
		// Extent Reports
		ExtentManagerObj = new ExtentManager();
		reports = ExtentManagerObj.GetExtent("Regression MOM TC_2");
	}

	@Test
	public void meth1() throws InterruptedException {
		test = reports.createTest("Login");
		driver.get("https://robotframework.org/robotframework/latest/RobotFrameworkUserGuide.html");
		System.out.println("Page opened");
		link.Click("introduction");
		Thread.sleep(3000);
		test.pass("Logged in Successfully");

	}

	@Test
	public void meth2() {
		test = reports.createTest("Login2");
		test.pass("Logged in Successfully");

	}

	@Test
	public void meth3() {
		test = reports.createTest("Login3");
		test.pass("Logged in Successfully");

	}

	@AfterClass
	public void teardown() {
		driver.quit();
		reports.flush();
	}
}
