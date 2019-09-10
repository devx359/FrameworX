package TestCases.SoftCell;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class GobalEditor {
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeClass
	public void setup(ITestContext context)
	{
		reports= (ExtentReports) context.getAttribute("extent");
	}
	@Test(priority=1)
	public void verifyMunoide()
	{

		test = reports.createTest("verifyMunoide");
		test.pass("verifyMunoide");
	}
	
	@Test(priority=2)
	public void verifyMunoide2()
	{
		test = reports.createTest("verifyMunoide2");
		test.pass("verifyMunoide2");
	}
	

	@AfterSuite
	public void teardown() {
		//driver.quit();
		reports.flush();
	}

}
