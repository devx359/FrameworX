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
	
	public UDF4_RadioButton(WebDriver drivers,ITestContext context) {
		
		super(drivers,context);
		driver = drivers;
		locator = new Locator();
		test = (ExtentTest)context.getAttribute("extent");
	}

	
}
