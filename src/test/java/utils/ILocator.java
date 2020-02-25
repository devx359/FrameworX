package utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;

public interface ILocator {

	public By getWebElement(String locator) throws FileNotFoundException, IOException ;
	
	public String getlocator(String locator) throws IOException, FileNotFoundException;
}
