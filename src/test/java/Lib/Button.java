package Lib;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ILocator;
import utils.Locator;

public class Button implements Element {

	String xpath;
	WebElement ele;
	ILocator locator;
	By buttonLocator;
	WebDriver driver;

	public Button(String path, WebDriver driver) {
		locator = new Locator();
		this.driver=driver;
		xpath=path;
	}

	@Override
	public void click() {
		try {
			buttonLocator = locator.getWebElement(xpath);
			WebDriverWait wait = new WebDriverWait(driver, 80);
			wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void jsClick(String strxpath, String filepath) {

		try {
			By LinkLocator = locator.getWebElement(strxpath);
			WebElement elementToClick = driver.findElement(LinkLocator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", elementToClick);
			elementToClick.sendKeys(filepath);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visible() {
		// TODO Auto-generated method stub

	}

}
