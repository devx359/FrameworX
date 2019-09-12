package TestCases.FiveG;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import userDefinedFunctions.UDF5_OtherFunctions;


public class Test1 {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	
	
	@BeforeClass
	public void setup(ITestContext context)
	{
		driver=(WebDriver) context.getAttribute("driver");
		report=(ExtentReports) context.getAttribute("extent");
		
	}
	
	@Test
	public void meth(ITestContext context) throws InterruptedException
	{
		test=report.createTest("nokia red test");
		context.setAttribute("test", test);
		driver.get("https://github.com/nokia/RED");
		Thread.sleep(2000);
		UDF5_OtherFunctions udf = new UDF5_OtherFunctions(context);
		
		udf.Click("issues");
		Thread.sleep(2000);
	}
	

}
