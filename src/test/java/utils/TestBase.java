package utils;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TestBase {

	public WebDriver driver;
	public ExtentReports reports;
	public ExtentTest test;
	ExtentManager ExtentManagerObj;

	@BeforeSuite
	public void setUp_Class(ITestContext context) {
		System.out.println("setUp_Class");

		// Extent Reports
		ExtentManagerObj = new ExtentManager();
		System.out.println("Setting report");
		reports = ExtentManagerObj.GetExtent("10.0.3 Test Results");
		context.setAttribute("extent", reports);
		// context.setAttribute("driver", driver);

		new File("target/screenshots").mkdir();
		System.setProperty("webdriver.chrome.driver","D:\\EclipsePhotonWorkspace\\Frameworx\\Drivers\\chromedriver_80.exe");
		driver = new ChromeDriver();

	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
		reports.flush();
	}

}
