package utils;

import java.io.FileOutputStream;
import java.time.LocalDateTime;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	private WebDriver driver; 
	  private String folderPath = "./target/screenshots/";
	 
	  public Screenshot(WebDriver driver)
	  { 
	    this.driver = driver;
	  }
	  public String capture(String fileName) 
	  { 
		String screenshotPath =null;  
	    try { 
	      String now = LocalDateTime.now().toString();
	      now = now.replace(":", "_")
	               .replace(";", "_")
	               .replace(".", "_");
	      System.out.println(folderPath + fileName + now + ".png");
	      FileOutputStream file = new FileOutputStream(
	                 folderPath + fileName + now + ".png");
	 
	      byte[] info = 
	      ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	 
	      file.write(info);
	      file.close(); 
	      
	      screenshotPath=folderPath + fileName + now + ".png" ;
	    } 
	    catch (Exception ex) {
	    	System.out.print("cannot create screenshot");
	      //throw new RuntimeException("cannot create screenshot;", ex);
	    }
	    return screenshotPath;
	  }
}
