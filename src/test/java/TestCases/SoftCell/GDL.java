package TestCases.SoftCell;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;

public class GDL {
	
	ExtentReports reports;
	ExtentTest test;
	ExtentManager ExtentManagerObj;
	
	@BeforeSuite
	public void setup(ITestContext context) {
		// Extent Reports
		ExtentManagerObj = new ExtentManager();
		reports = ExtentManagerObj.GetExtent("10.0.3 Test Results");
		context.setAttribute("extent", reports);
	}
	
	/*@BeforeClass
	public void setup(ITestContext context)
	{
		reports=(ExtentReports) context.getAttribute("extent");
	}*/
	@Test(priority=1)
	public void creatingProperty() {
		test = reports.createTest("creatingProperty");
		test.pass("creatingProperty passed ");

	}
	
	@Test(priority=1)
	public void deleteProperty() {
		test = reports.createTest("deleteProperty");
		test.pass("deleteProperty passed ");

	}
	
	@AfterSuite
	public void teardown() {
		//driver.quit();
		reports.flush();
	}

}
