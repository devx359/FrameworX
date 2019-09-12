package TestCases.FiveG;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import userDefinedFunctions.UDF2_Link;
import utils.DriverUtil;
import utils.ExtentManager;
/**
 * Test test cases
 * @author haldard
 * 
 *
 */

public class FiveGExportImport {
	WebDriver driver;
	UDF2_Link link;
	ExtentReports reports;
	ExtentTest test;
	ExtentManager ExtentManagerObj;


	@BeforeSuite
	public void setup(ITestContext context) {
		driver = new DriverUtil().DriverSetup("chrome");
		
		// Extent Reports
		ExtentManagerObj = new ExtentManager();
		reports = ExtentManagerObj.GetExtent("10.0.3 Test Results");
		context.setAttribute("extent", reports);
		context.setAttribute("driver", driver);
	}

	@Test(priority=1)
	public void FileCompare5GFilter() throws InterruptedException {
		//reports.setAnalysisStrategy(AnalysisStrategy.CLASS);
		test = reports.createTest("FileCompare5GFilter");
		/*driver.get("https://robotframework.org/robotframework/latest/RobotFrameworkUserGuide.html");
		System.out.println("Page opened");
		link.Click("introduction");
		Thread.sleep(3000);*/
		test.pass("FileCompare5GFilter passed ");

	}

	@Test(priority=2)
	public void FileCompare5GRepeater() {
		test = reports.createTest("FileCompare5GRepeater");
		test.fail("FileCompare5GRepeater passed ");

	}

	@Test(priority=3)
	public void FileCompare5GBearer() {
		test = reports.createTest("FileCompare5GBearer");
		test.pass("FileCompare5GBearer passed ");

	}

	@AfterSuite
	public void teardown() {
		driver.quit();
		reports.flush();
	}
}
