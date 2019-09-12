package userDefinedFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import com.aventstack.extentreports.ExtentTest;

import utils.Locator;

public class UDF4_RadioButton extends UDF3_TextBox{
	WebDriver driver;
	Locator locator;
	ExtentTest test;
	
	public UDF4_RadioButton(ITestContext context) {
		
		super(context);
		driver = (WebDriver) context.getAttribute("driver");	
		locator = new Locator();
		test = (ExtentTest)context.getAttribute("test");
	}

	
}
