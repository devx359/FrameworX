package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.PathUtility;

public class Locator {

	public By getWebElement(String locator) throws FileNotFoundException, IOException {

		String strlocator;
		By ByElement = null;
		strlocator = getlocator(locator).trim();
		String locatorType = strlocator.split(";")[0];
		String locatorValue = strlocator.split(";")[1];

		// System.out.println("locatorType:" + locatorType);
		// System.out.println("locatorValue:" + locatorValue);

		try {
			switch (locatorType) {
			case "id":
				ByElement = By.id(locatorValue);
				break;
			case "xpath":
				ByElement = By.xpath(locatorValue);
				break;
			case "name":
				ByElement = By.name(locatorValue);
				break;
			case "css":
				ByElement = By.cssSelector(locatorValue);
				break;
			case "linktext":
				ByElement = By.linkText(locatorValue);
				break;
			case "tag":
				ByElement = By.className(locatorValue);
				break;
			case "class":
				ByElement = By.className(locatorValue);
				break;

			}
		} catch (NoSuchElementException e) {

			System.out.println("Unknown locator" + locatorValue);

		}
		return ByElement;
	}

	public String getlocator(String locator) throws IOException, FileNotFoundException {
		String xpath;

		Properties prop = new Properties();
		String path=PathUtility.OR_File;
		//System.out.println(path);
		FileInputStream file = new FileInputStream(path);
		//FileInputStream file = new FileInputStream(".\\src\\test\\java\\config\\OR_E1.properties");
		prop.load(file);
		xpath = prop.getProperty(locator);
		return xpath;
	}
}
